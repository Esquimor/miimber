/**
 * Test profile page
 */

import { v4 as uuidv4 } from "uuid";

describe("Porfile", () => {
  before(() => {
    cy.login("admin");
    cy.saveLocalStorage();
  });

  beforeEach(() => {
    cy.restoreLocalStorage();
    cy.visit("/settings/profile");
    cy.get("input#SettingsProfile-email.input").as("email");
    cy.get("input#SettingsProfile-firstName.input").as("firstName");
    cy.get("input#SettingsProfile-lastName.input").as("lastName");
    cy.get("button#SettingsProfile-update").as("update");
  });

  afterEach(() => {
    cy.reload(true);
  });

  it("Email input", () => {
    // Test empty email
    cy.get("@email")
      .clear()
      .blur();
    cy.get("@email").should("have.class", "is-danger");

    // Test bad email
    cy.get("@email")
      .clear()
      .type("badEmail")
      .blur();
    cy.get("@email").should("have.class", "is-danger");

    // Test good email
    cy.get("@email")
      .clear()
      .type("good@email.com")
      .blur();
    cy.get("@email").not("have.class", "is-danger");
  });

  it("FirstName input", () => {
    // Empty firstName
    cy.get("@firstName")
      .clear()
      .blur();
    cy.get("@firstName").should("have.class", "is-danger");

    // Test good firstName
    cy.get("@firstName")
      .clear()
      .type("firstName")
      .blur();
    cy.get("@firstName").not("have.class", "is-danger");
  });

  it("LastName input", () => {
    // Empty lastName
    cy.get("@lastName")
      .clear()
      .blur();
    cy.get("@lastName").should("have.class", "is-danger");

    // Test good lastName
    cy.get("@lastName")
      .clear()
      .type("lastName")
      .blur();
    cy.get("@lastName").not("have.class", "is-danger");
  });

  it("Can submit", () => {
    // Empty input
    cy.get("@email").clear();
    cy.get("@firstName").clear();
    cy.get("@lastName").clear();
    cy.get("@update").should("be.disabled");

    // Only email
    cy.get("@email").type("good@email.com");
    cy.get("@update").should("be.disabled");
    cy.get("@email").clear();

    // Only FirstName
    cy.get("@firstName").type("FirstName");
    cy.get("@update").should("be.disabled");
    cy.get("@firstName").clear();

    // Only LastName
    cy.get("@lastName").type("LastName");
    cy.get("@update").should("be.disabled");
    cy.get("@lastName").clear();
  });

  it("Submit", () => {
    cy.get("@email").type(`${uuidv4()}-test@meember.com`);
    cy.get("@firstName").type("firstName");
    cy.get("@lastName").type("lastName");
    cy.get("@update").click();
  });
});
