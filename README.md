<div align="center">
<h1>Server for the Guide Helper app</h1></div>

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/371bdc51df794cf1a8203097477b1f61)](https://www.codacy.com/gh/guide-helper/guide-helper-server/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=guide-helper/guide-helper-server&amp;utm_campaign=Badge_Grade)
  
## Description
Spring Boot server using MySQL database

## Functionality
*  REST API for actions with tours, users, and orders based on Three Layer Architecture
*  Client-guide chat via storing chat history in Firebase
*  RAKE(Rapid Automatic Keyword Extraction) algorithm for extracting keywords from dialog messages

## Install
-   Clone repository: `git clone https://github.com/chikaldiriki/Guide-Helper-Server.git`
-   Install docker and docker-compose

## Run
-   `docker compose build`
-   `docker compose up -d`

## API
-   [link](swagger-ui.html)
