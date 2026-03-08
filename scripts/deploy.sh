#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

ENV_FILE="${ENV_FILE:-${PROJECT_ROOT}/.env}"
if [[ -f "${ENV_FILE}" ]]; then
  set -a
  # shellcheck disable=SC1090
  source "${ENV_FILE}"
  set +a
fi

DB_NAME="${DB_NAME:-locationenligne}"

APP_NAME="${APP_NAME:-MySiteLOCATION}"
APP_URL="${APP_URL:-http://localhost:8080/${APP_NAME}/}"

TOMCAT_SERVICE="${TOMCAT_SERVICE:-tomcat10}"
TOMCAT_WEBAPPS_DIR="${TOMCAT_WEBAPPS_DIR:-/var/lib/tomcat10/webapps}"

DB_URL="${DB_URL:-jdbc:mysql://localhost:3306/${DB_NAME}?serverTimezone=UTC}"
DB_USER="${DB_USER:-locationapp}"
DB_PASSWORD="${DB_PASSWORD:-}"

INIT_SQL="${INIT_SQL:-${PROJECT_ROOT}/scripts/init.sql}"
DB_CONF_DIR="/etc/systemd/system/${TOMCAT_SERVICE}.service.d"
DB_CONF_FILE="${DB_CONF_DIR}/db.conf"

if ! command -v mvn >/dev/null 2>&1; then
  echo "mvn n'est pas installe."
  exit 1
fi

if ! command -v curl >/dev/null 2>&1; then
  echo "curl n'est pas installe."
  exit 1
fi

if ! command -v mysql >/dev/null 2>&1; then
  echo "mysql client non trouve. Installe mysql-server ou mariadb-server."
  exit 1
fi

if [[ ! "${DB_NAME}" =~ ^[A-Za-z0-9_]+$ ]]; then
  echo "DB_NAME invalide: utilise uniquement lettres, chiffres et underscore."
  exit 1
fi

if [[ ! "${DB_USER}" =~ ^[A-Za-z0-9_]+$ ]]; then
  echo "DB_USER invalide: utilise uniquement lettres, chiffres et underscore."
  exit 1
fi

if [[ -z "${DB_PASSWORD}" ]]; then
  echo "DB_PASSWORD est vide. Definis-le dans ${ENV_FILE} ou via variable d'environnement."
  exit 1
fi

sql_escape_literal() {
  printf "%s" "$1" | sed "s/'/''/g"
}

DB_PASSWORD_ESCAPED="$(sql_escape_literal "${DB_PASSWORD}")"

echo "[1/6] Build Maven..."
cd "${PROJECT_ROOT}"
mvn clean package

WAR_PATH="${PROJECT_ROOT}/target/${APP_NAME}.war"
if [[ ! -f "${WAR_PATH}" ]]; then
  echo "WAR introuvable: ${WAR_PATH}"
  exit 1
fi

if [[ "${SKIP_DB_INIT:-0}" != "1" ]]; then
  echo "[2/6] Initialisation DB via ${INIT_SQL}..."
  if [[ ! -f "${INIT_SQL}" ]]; then
    echo "Fichier SQL introuvable: ${INIT_SQL}"
    exit 1
  fi

  sudo mysql <<SQL
CREATE DATABASE IF NOT EXISTS \`${DB_NAME}\` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS '${DB_USER}'@'localhost' IDENTIFIED BY '${DB_PASSWORD_ESCAPED}';
CREATE USER IF NOT EXISTS '${DB_USER}'@'%' IDENTIFIED BY '${DB_PASSWORD_ESCAPED}';
GRANT ALL PRIVILEGES ON \`${DB_NAME}\`.* TO '${DB_USER}'@'localhost';
GRANT ALL PRIVILEGES ON \`${DB_NAME}\`.* TO '${DB_USER}'@'%';
FLUSH PRIVILEGES;
SQL

  sudo mysql "${DB_NAME}" < "${INIT_SQL}"
else
  echo "[2/6] Initialisation DB ignoree (SKIP_DB_INIT=1)."
fi

echo "[3/6] Configuration DB pour Tomcat (${DB_CONF_FILE})..."
sudo mkdir -p "${DB_CONF_DIR}"
sudo install -m 600 /dev/null "${DB_CONF_FILE}"
sudo tee "${DB_CONF_FILE}" >/dev/null <<EOF
[Service]
Environment="DB_URL=${DB_URL}"
Environment="DB_USER=${DB_USER}"
Environment="DB_PASSWORD=${DB_PASSWORD}"
EOF
sudo chmod 600 "${DB_CONF_FILE}"

echo "[4/6] Reload systemd..."
sudo systemctl daemon-reload

echo "[5/6] Deploiement WAR et restart Tomcat..."
sudo cp "${WAR_PATH}" "${TOMCAT_WEBAPPS_DIR}/${APP_NAME}.war"
sudo systemctl restart "${TOMCAT_SERVICE}"

echo "[6/6] Attente de disponibilite de ${APP_URL}..."
for _ in $(seq 1 60); do
  if curl -sfI "${APP_URL}" >/dev/null; then
    echo "Application disponible: ${APP_URL}"
    curl -I "${APP_URL}"
    exit 0
  fi
  sleep 1
done

echo "Le service n'a pas repondu dans le delai attendu."
echo "Diagnostic rapide:"
systemctl status "${TOMCAT_SERVICE}" --no-pager || true
exit 1
