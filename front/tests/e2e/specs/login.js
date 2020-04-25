/**
 * Test Login page
 */

import { v4 as uuidv4 } from "uuid";

describe("Login", () => {
  beforeEach(() => {
    cy.visit("/login");
    cy.get("input#SignLogin-email.input").as("email");
    cy.get("input#SignLogin-password.input").as("password");
    cy.get("label#SignLogin-remember input").as("remember");
    cy.get("label#SignLogin-remember span.check").as("remember-visible");
    cy.get("button#SignLogin-submit").as("submit");
  });

  it("Test email input false", () => {
    cy.get("@email")
      .type("badEmail")
      .blur();
    cy.get("@email").should("have.class", "is-danger");

    cy.get("@email")
      .clear()
      .blur();
    cy.get("@email").should("have.class", "is-danger");
  });

  it("Test email input true value", () => {
    cy.get("@email")
      .type("good@email.com")
      .blur();
    cy.get("@email").not("have.class", "is-danger");
  });

  it("Test password empty value", () => {
    cy.get("@password")
      .clear()
      .blur();
    cy.get("@password").should("have.class", "is-danger");
  });

  it("Can click to submit", () => {
    // Email
    cy.get("@submit").should("be.disabled");
    cy.get("@email").type("good@email.com");
    cy.get("@submit").should("be.disabled");
    cy.get("@email").clear();
    cy.get("@submit").should("be.disabled");

    // Password
    cy.get("@password").type("secret");
    cy.get("@submit").should("be.disabled");
    cy.get("@password").clear();
    cy.get("@submit").should("be.disabled");

    // Password and Email
    cy.get("@email").type("good@email.com");
    cy.get("@password").type("secret");
    cy.get("@submit").not("be.disabled");
  });

  it("Remember", () => {
    cy.get("@remember").uncheck();
    cy.get("@remember-visible").click();
    cy.get("@remember").check();
    cy.get("@remember-visible").click();
    cy.get("@remember").uncheck();
  });

  it("Login", () => {
    const email = `${uuidv4()}-test@meember.com`;
    const password = "superSecret";
    cy.request("POST", "http://localhost:8000/test/register", {
      email,
      password
    }).then(() => {
      cy.get("@email").type(email);
      cy.get("@password").type(password);
      cy.get("@submit").click();
      cy.url().should("include", "/session");
    });
  });
});
