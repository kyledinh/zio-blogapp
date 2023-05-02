#/bin/bash

# USAGE: source .{this}env-localdev.sh

# s"postgres://$username:$password@$host:$port/$dbname"
export DATABASE_URL="postgres://postgres:password@localhost:5432/blogapp" 
export BLOGAPPBACKEND_PORT=4000
export BLOGAPPBACKEND_HOST=localhost
export BLOGAPPBACKEND_RUNMODE=Prod
export BLOGAPPBACKEND_LOGLEVEL=Debug
