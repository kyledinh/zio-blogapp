# Build for Kubernetes Guide
> Build container for backend api and frontend webserver and deploy to Kubernetes Cluster

The backend api Docker image is built with the sbt-native-packager plugin. And the frontend is built with a traditional Dockerfile to base Nginx image.

## Software Used 

Software            | Version              | Install
--------------------|----------------------|-----------------------------------------------
Docker Desktop      | 4.3.x                | https://www.docker.com/products/docker-desktop
Kubernetes          | v1.24.2              | installed with Docker Desktop
kubectl             | GitVersion:"v1.24.2" |
sbt                 | 1.8.2                | https://www.scala-sbt.org/download.html
sbt-native-packager | 1.9.9                | https://www.scala-sbt.org/sbt-native-packager/

If you are using Docker Desktop, you need to enable Kubernetes under `Preferences > Kubernetes > Enable Kubernetes`.

<br><hr><br>

## Build Steps 

> In this example, we will deploy to the localhost Docker Desktop's Kubernetes cluster, 

- Check the semver and gittag
  - view/edit `/sem-version` file in the root directory
  - `make check` will print out the semver and gittag the build will use
  ```
  SEMVER: 0.1.1
  GITTAG: ef9867e-dirty
  ```
- Build docker images -  `make docker-build` is a combo 2 step target/alias that will build the backend API and frontend webserver
  - Step 1: `sbt docker:publishLocal` uses the backend API with the `addSbtPlugin("com.github.sbt"        % "sbt-native-packager"      % "1.9.9")` 
  - Step 2: uses the script in `docker/build-blogapp-ngnix-frontend.sh` to build the frontend in a base Nginx container 
- Push to container repository: This is to push to a remote Container repository like [Docker Hub](https://hub.docker.com/u/kyledinh), to allow remote Kubernetes cluster to access. This step can be skipped for local development. 
  - `make docker-push` 
- Update the deployment.yamls
  - Update the `spec > template > containers > image` to the latest version 
  - Edit `kubernetes/desktop/deployment-blogapp-api.yaml`
  - Edit `kubernetes/desktop/deployment-blogapp-web.yaml`
- Update Kubernetes cluster with kubectl
  - `cd kubernetes` - change dirctory to run kubectl from correct path  
  - use `desktop-demo.sh` (a wrapper script for the specific environment and namespace), this one is for local development using the `demo` namespace 
  - `./desktop-demo.sh info`
  - `./desktop-demo.sh up` 
  - See [file](../../kubernetes/desktop-demo.sh), there are many other helpful options available, or view the [Kubernetes Tool Script](#kubernetes-tool-script) for more 

<br><hr><br>

## Kubernetes Tool Script 

> This script (kubernetes/desktop-demo.sh) was written to provide some organization to common and repeative kubectl tasks. Each version of the script would be specified to a Context and Namespace, this provides a safety check to prevent issuing kubectl command to the wrong Kubernetes cluster that you have access to.

<br>

If you do not have the correct Context for script it will list your current context and available contexts, then you can switch to the localdev context with this command: `kubectl config use-context docker-desktop`

<br>

In this example, my current context is pointed to a Linode Kubernetes Cluster, but the command will list the other available contexts.
```
*[issue-18-kubernetes][~/src/github.com/kyledinh/zio-blogapp/kubernetes]$ ./desktop-demo.sh info

## Wrong current context: lke38710-ctx!
Aborting script. No actions taken.

Current Context: 
lke38710-ctx

Available contexts: 
CURRENT   NAME                                      CLUSTER                                 
          do-sfo3-k8s-kyledinh                      do-sfo3-k8s-kyledinh                                
          docker-desktop                            docker-desktop                                     
          gke_formaterra-361422_us-east1-c_tf-gke   gke_formaterra-361422_us-east1-c_tf-gke   
          gke_k8s-staging-913590_us-west2_gke       gke_k8s-staging-913590_us-west2_gke          
          gke_protoserve_us-central1-c_cluster-1    gke_protoserve_us-central1-c_cluster-1    
*         lke38710-ctx                              lke38710                                  

To switch context use command: kubectl config use-context TARGET_CONTEXT
```

Common Commands

Command                            | Details
-----------------------------------|---------------------------------------------------------------
`desktop-demo.sh info`             | List info on deployments, servics, pods, namespace and secrets
`desktop-demo.sh init`             | Run once to create namespace `demo`
`desktop-demo.sh init-db`          | Run once to seed Postgres database
`desktop-demo.sh up`               | To deploy deployment, services and pods to your Kube cluster
`desktop-demo.sh down`             | To remove your deployment from the Kube cluster
`desktop-demo.sh log <SEARCHTERM>` | To print the log from a pod that matches the SEARCHTERM
`desktop-demo.sh ex <SEARCHTERM>`  | Open a bash session into the pod that matches the SEARCHTERM


Note: This is a very simple/striped down deployment for localdev; for deployment to multiple environments like Testing, Staging and Production, you should look into Helm charts. A topic to cover at a later time.

<br><hr><br>

## Common Docker Commands

Command                          | Details
---------------------------------|--------------------------------------------------------------
`docker ps`                      | list running Docker containers
`docker images \| grep blogapp`  | list Docker images available that match `blogapp` in the name
`docker push <IMAGE ID>`         | push a Docker image id to remote repository
`docker push <REPOSITORY>:<TAG>` | push a Docker image by name to remote repository
