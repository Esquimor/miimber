export default [
  {
    path: "/login",
    name: "login",
    component: () => import("@sign/views/Login")
  },
  {
    path: "/register",
    name: "register",
    component: () => import("@sign/views/Register")
  },
  {
    path: "/register-completed",
    name: "register-completed",
    component: () => import("@sign/views/RegisterCompleted")
  },
  {
    path: "/register-validated",
    name: "register-validated",
    component: () => import("@sign/views/RegisterValidated")
  },
  {
    path: "/password-forgotten",
    name: "password-forgotten",
    component: () => import("@sign/views/PasswordForgotten")
  },
  {
    path: "/reset-password",
    name: "reset-password",
    component: () => import("@sign/views/ResetPassword")
  },
  {
    path: "/invitation",
    name: "invitation",
    component: () => import("@sign/views/Invitation")
  }
];
