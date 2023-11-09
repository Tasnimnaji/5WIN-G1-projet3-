# Use an official Node runtime as a parent image
FROM node:14

# Set the working directory in the container
WORKDIR /tpAchatProject

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Install app dependencies
RUN npm install

# Copy the application files to the container
COPY . .

# Build the Angular app
RUN ng build --prod

# Expose port 80
EXPOSE 80

# Command to run the application
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "80"]
