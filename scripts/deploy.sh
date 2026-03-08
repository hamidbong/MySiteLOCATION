#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

APP_NAME="${APP_NAME:-MySiteLOCATION}"
APP_URL="${APP_URL:-http://localhost:8080/${APP_NAME}/}"

TOMCAT_SERVICE="${TOMCAT_SERVICE:-tomcat10}"
TOMCAT_WEBAPPS_DIR="${TOMCAT_WEBAPPS_DIR:-/var/lib/tomcat10/webapps}"

DB_URL="${DB_URL:-jdbc:mysql://localhost:3306/locationenligne?serverTimezone=UTC}"
DB_USER="${DB_USER:-locationapp}"
DB_PASSWORD="${DB_PASSWORD:-locationpass}"

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
  sudo mysql < "${INIT_SQL}"
else
  echo "[2/6] Initialisation DB ignoree (SKIP_DB_INIT=1)."
fi

echo "[3/6] Configuration DB pour Tomcat (${DB_CONF_FILE})..."
sudo mkdir -p "${DB_CONF_DIR}"
sudo tee "${DB_CONF_FILE}" >/dev/null <<EOF
[Service]
Environment="DB_URL=${DB_URL}"
Environment="DB_USER=${DB_USER}"
Environment="DB_PASSWORD=${DB_PASSWORD}"
EOF

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
