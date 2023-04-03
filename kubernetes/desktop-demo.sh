#!/bin/bash

## ENVs and VARS
TARGET=docker-desktop    ## EXAMPLE OF A LINODE KUBERNETES CLUSTER TARGET
NAMESPACE=demo           ## EXAMPLE OF A NAMESPACE THAT IS NOT DEFAULT
THIS_FILE=$0

## CHECKS
function fn_context {
	echo; echo "Current Context: " 
	kubectl config current-context
	echo; echo "Available contexts: "
	kubectl config get-contexts
	echo;
	echo "To switch context use command: kubectl config use-context TARGET_CONTEXT"
}

if kubectl config current-context | grep -q $TARGET
then 
	echo; echo "## Current context $TARGET"
else
	echo; echo "## Wrong current context: $(kubectl config current-context)!"
	echo "Aborting script. No actions taken."
	fn_context;
	exit 1
fi

if kubectl get ns $NAMESPACE -o json | jq .status.phase -r
then 
	echo; echo " You have demo namespace"
else 
	echo; echo "Please run to create $NAMESPACE namespace: kubectl create namespace $NAMESPACE"
fi

function fn_admin {
	echo "TODO: Does nothing."
}

function fn_check {
	kubectl get ns $NAMESPACE -o json | jq .status.phase -r	
}

function fn_chrome {
	open -a "Google Chrome" https://blogapp.kyledinh.com/
	open -a "Google Chrome" https://blogapi.kyledinh.com/scrawls
}

function fn_debugcerts {
	echo; echo "kubectl get orders --all-namespaces"
	kubectl get orders --all-namespaces
	echo; echo "kubectl get challenges --all-namespaces"
	kubectl get challenges --all-namespaces
	echo; echo "kubectl get certificates --all-namespaces"
	kubectl get certificates --all-namespaces
	echo; echo "Try: kubectl describe certificate <certificate name> -n <certificate namespace>"
}

function fn_down {
	kubectl delete deployment blogapp-api -n $NAMESPACE
	kubectl delete deployment blogapp-database -n $NAMESPACE
	kubectl delete deployment blogapp-web -n $NAMESPACE
	# kubectl delete cm cm-webtest-index-html -n $NAMESPACE
}

function fn_ex {
	POD=$(kubectl get pods -n web | grep $1 | awk '{print $1}')
    echo "kubectl exec -it -n web $POD -- bash"
    kubectl exec -it -n $NAMESPACE $POD -- bash
}

function fn_help {
	echo; echo "USAGE: $0 <arguments>. Try '$0 up | down | info | log | ... '"
	tail -50 $THIS_FILE | grep ")"
}

function fn_info {
	echo;  echo "Namespace:"
	kubectl get namespace 
	echo; kubectl get ingress -n $NAMESPACE
	echo; echo "Deployments"
	kubectl get deployments -n $NAMESPACE
	echo; echo "Services"
	kubectl get svc  -n $NAMESPACE
	echo; echo "Pods"
	kubectl get pods -n $NAMESPACE -o wide
	echo; echo "Config Maps"
	kubectl get cm -n $NAMESPACE
	echo; echo "Secrets"
	kubectl get secret -n $NAMESPACE
	echo; echo "All pods, all namespaces:"
	kubectl get pods --all-namespaces --output=custom-columns="SPACE:.metadata.namespace, NAME:.metadata.name, IMAGE:.spec.containers[*].image"
}

function fn_init {
	kubectl create namespace $NAMESPACE
}

function fn_init_db {
	POD=$(kubectl get pods -n $NAMESPACE | grep blogapp-database | awk '{print $1}')
	kubectl exec -n $NAMESPACE $POD -- psql -U postgres -d blogapp -c "`cat ../backend/src/main/resources/db/info.sql`"
	kubectl exec -n $NAMESPACE $POD -- psql -U postgres -d blogapp -c "`cat ../backend/src/main/resources/db/migration/V1__create_blogapp.sql`"
	kubectl exec -n $NAMESPACE $POD -- psql -U postgres -d blogapp -c "`cat ../backend/src/main/resources/db/migration/V2__add_fixtures.sql`"
	kubectl exec -n $NAMESPACE $POD -- psql -U postgres -d blogapp -c "\d"
}

function fn_log {
	POD=$(kubectl get pods -n $NAMESPACE | grep $1 | awk '{print $1}')
	echo; echo "kubectl logs $POD -n $NAMESPACE"
    kubectl logs $POD -n $NAMESPACE
}

function fn_up {
	kubectl apply -f desktop/deployment-blogapp-database.yaml -n $NAMESPACE
	kubectl apply -f desktop/deployment-blogapp-web.yaml -n $NAMESPACE
	kubectl apply -f desktop/deployment-blogapp-api.yaml -n $NAMESPACE
}

## MAIN/SWITCH
echo ".... in the MAIN/SWITCH"

if [ "$#" -eq 0 ]; then
	COMMAND=info
	echo; echo "You need to supply an argument. Defaulting to <info>. Try '$0 help' for more options."; echo
else
	COMMAND=$1
fi

case "$COMMAND" in
	admin)
		fn_admin;;
	check)
		fn_check;;
	chrome)
		fn_chrome;;
	debugcerts)
		fn_debugcerts;;
	down)
		fn_down;;
	ex)
		fn_ex ${@:2};;
	help)
		fn_help;;
	info) 
		fn_info;;
	init)
		fn_init;;
	init-db)
		fn_init_db;;
	log) 
		fn_log ${@:2};;
	up)
		fn_up;;
	*) 
		fn_info;;
esac
