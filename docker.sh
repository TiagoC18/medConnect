#!/bin/bash

# Parar e remover os contêineres
docker compose down

# Remover volumes não utilizados
docker volume prune -af

# Limpar o sistema Docker
docker system prune -af

# Remover todos os contêineres e volumes associados
docker compose rm -af

# Iniciar os contêineres
docker compose up
