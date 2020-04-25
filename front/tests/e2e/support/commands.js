// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************

import { v4 as uuidv4 } from "uuid";
import "cypress-localstorage-commands";

const adminEmail = `${uuidv4()}-admin-test@meember.com`;
const memberEmail = `${uuidv4()}-member-test@meember.com`;

Cypress.Commands.add("login", userType => {
  // this is an example of skipping your UI and logging in programmatically

  // setup some basic types
  // and user properties
  const types = {
    admin: {
      email: adminEmail,
      password: "secret"
    },
    member: {
      name: memberEmail,
      password: "secret"
    }
  };

  // grab the user
  const user = types[userType];

  // create the user first in the DB
  cy.request({
    url: `http://localhost:8000/test/register`, // assuming you've exposed a seeds route
    method: "POST",
    body: user
  })
    .its("body")
    .then(() => {
      cy.request({
        url: `http://localhost:8000/login`,
        method: "POST",
        body: {
          email: user.email,
          password: user.password
        }
      })
        .its("body")
        .then(body => {
          cy.setLocalStorage("token", body.token);
        });
    });
});

Cypress.Commands.add("createOrganization", () => {
  cy.request({
    url: "http://localhost:8000/test/organization/",
    method: "POST",
    body: {
      name: uuidv4()
    },
    auth: {
      bearer: `${localStorage.getItem("token")}`
    }
  })
    .its("body")
    .then(body => {
      cy.setLocalStorage("organizationId", body.id);
    });
});
