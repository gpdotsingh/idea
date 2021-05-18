
# Run application by docker:
Ref: https://www.youtube.com/watch?v=fvEWoy1xOvo
1) Create application with actuator endpoints and spring security and make endpoints open for all
⦁		To create actuator endpoints with spring secutrity add below dependecyin pom
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
⦁	Add below dependency for spring security
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

⦁	Add bean to expose the actuator endpoints
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((requests) ->
			requests.anyRequest().permitAll());
	http.httpBasic();
	return http.build();
}
⦁	make final name in plugin configuration with which you want to create Jar file
		<finalName>login-manager</finalName>
2) Run the above file in docker.
⦁	Add file in project with docker.yml
⦁	In docker.yml define details like from which java version it need to run as mentioned below
		FROM openjdk:8
		ADD target/login-manager.jar login-manager.jar
		EXPOSE 8086
		ENTRYPOINT ["java","-jar","login-manager.jar"]
⦁	add Image todocker repo onlocal with tag name, run below commandon terminal
	docker build . -t login-manager
⦁	Run below command to run docker file
		docker run -p 8086:8086 --name loginManager -d login-manager
		loginManager is name for your application in docker
		login-manager is image name
⦁	Run http://localhost:8086/actuator
-----------------------------------------------
To run front end on nginx docker
- Add dockerfile in root into 
- Add below command to it.
# Stage 1: Compile and Build angular codebase

# Use official node image as the base image
FROM node:latest as build

# Set the working directory inside docker
WORKDIR /usr/local/app

# Add the source code to app
COPY ./ /usr/local/app/

# Install all the dependencies
RUN> npm install

# Generate the build of the application
RUN > npm run build

# Stage 2: Serve app with nginx server
# Use official nginx image as the base image
FROM nginx:latest

# Copy the build output to replace the default nginx contents.
COPY --from=build /usr/local/app/dist/idea /usr/share/nginx/html

# Expose port 80
EXPOSE 80

## Build the image in docker by . means building from current direcotry
generate the dist file :  ng build --prod  
docker build . -t idea-fe
## Run below command
docker run -p 80:80 --name ideafe -d idea-fe

-------------------------------------------------
Some important command and links
⦁	Run docker with network
Create and start the container as a detached process. The --rm option means to remove the container once it exits/stops. The -d flag means to start the container detached (in the background).
docker run --rm -d --network host --name my_nginx nginx
⦁	docker system prune -a   // Delete all images not running
⦁	kubectl exec -it login-manager-pod -c <container-name> -- /bin/sh    ## go inside the pod to check the container name dont get confused with image name

// Remove docker image and place new container
⦁	docker container ls -a
⦁	docker stop 757566790cb1
⦁	docker rm 757566790cb1
⦁	docker image rm 4b5036954db0



------------------------------------------------------
## Configure k8s for your images

1) Create deployment for backend
	run below command to generate the deployment from image
		- Make sure image pull policy is never
kubectl create deployment login-manager-deployment --image=login-manager  --replicas=3 --port=8086 --dry-run=client -o yaml> login-manager-deployment.yaml
Add         imagePullPolicy: Never
Change pod name by default it take container name

2) Create service
	Run command 
- kubectl expose deployment/loginmanagerdeployment --dry-run=client -o yaml> login-manager-service.yaml

3) Create frontend deployment 
kubectl create deployment idea-fe-deployment --image=idea-fe  --replicas=2 --port=80 --dry-run=client -o yaml> idea-fe-deployment.yaml
Make sure image pull policy is never
Add   imagePullPolicy: Never
Change pod name by default it take container name

4) Create frontend service that is nodeport service
	Run command "kubectl expose deployment ideafedeployment --type=LoadBalancer  --name=idea-fe-lb-svc --dry-run=client -o yaml>idea-fe-lb-svc.yaml "




