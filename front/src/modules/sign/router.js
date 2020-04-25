import Login from "@sign/views/Login";
import Register from "@sign/views/Register";
import RegisterCompleted from "@sign/views/RegisterCompleted";
import RegisterValidated from "@sign/views/RegisterValidated";
import PasswordForgotten from "@sign/views/PasswordForgotten";
import ResetPassword from "@sign/views/ResetPassword";

export default [
  {
    path: "/login",
    name: "login",
    component: Login
  },
  {
    path: "/register",
    name: "register",
    component: Register
  },
  {
    path: "/register-completed",
    name: "register-completed",
    component: RegisterCompleted
  },
  {
    path: "/register-validated",
    name: "register-validated",
    component: RegisterValidated
  },
  {
    path: "/password-forgotten",
    name: "password-forgotten",
    component: PasswordForgotten
  },
  {
    path: "/reset-password",
    name: "reset-password",
    component: ResetPassword
  }
];
