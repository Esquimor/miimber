/**
 * Try to register an user
 */
import { v4 as uuidv4 } from "uuid";

describe("Register", () => {
  beforeEach(() => {
    cy.visit("/register");
    cy.get("input#SignRegister-email.input").as("email");
    cy.get("input#SignRegister-password.input").as("password");
    cy.get("input#SignRegister-confirm.input").as("confirm");
    cy.get("button#SignRegister-submit").as("submit");
  });

  it("Test email input false value", () => {
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

  it("Test confirm empty value", () => {
    cy.get("@confirm")
      .clear()
      .blur();
    cy.get("@confirm").should("have.class", "is-danger");
  });

  it("Test password doesn't match confirm", () => {
    cy.get("@password").type("secret");
    cy.get("@confirm")
      .type("notSecret")
      .blur();
    cy.get("@password").should("have.class", "is-danger");
    cy.get("@confirm").should("have.class", "is-danger");
  });

  it("Test password match confirm", () => {
    cy.get("@password").type("secret");
    cy.get("@confirm")
      .type("secret")
      .blur();
    cy.get("@password").not("have.class", "is-danger");
    cy.get("@confirm").not("have.class", "is-danger");
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
    cy.get("@confirm").type("secret");
    cy.get("@submit").should("be.disabled");
    cy.get("@password").clear();
    cy.get("@confirm").clear();
    cy.get("@submit").should("be.disabled");

    // All
    cy.get("@email").type("good@email.com");
    cy.get("@password").type("secret");
    cy.get("@confirm").type("secret");
    cy.get("@submit").not("be.disabled");
  });

  it("Register a user", () => {
    cy.get("@email").type(`${uuidv4()}-test@meember.com`);
    cy.get("@password").type("secret");
    cy.get("@confirm").type("secret");

    cy.get("@submit").click();
    cy.url().should("include", "/register-completed");
  });
});
