#!/bin/bash

echo "http://localhost:4000/scrawls/person/0ecbdfbd-6784-4396-9f65-efd86f568267"
curl http://localhost:4000/scrawls/person/0ecbdfbd-6784-4396-9f65-efd86f568267
echo; echo; 

echo "http://localhost:4000/persons"
curl http://localhost:4000/persons
echo; echo; 

echo "http://localhost:4000/scrawls"
curl http://localhost:4000/scrawls
echo; echo; 
