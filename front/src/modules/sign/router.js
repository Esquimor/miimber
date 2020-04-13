import Login from "@sign/views/Login";
import Register from "@sign/views/Register";
import RegisterCompleted from "@sign/views/RegisterCompleted";

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
  }
];
