import Login from "@sign/views/Login";
import Register from "@sign/views/Register";
import RegisterCompleted from "@sign/views/RegisterCompleted";
import RegisterValidated from "@sign/views/RegisterValidated";

export default [
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/register",
    name: "register",
    component: Register,
  },
  {
    path: "/register-completed",
    name: "register-completed",
    component: RegisterCompleted,
  },
  {
    path: "/register-validated",
    name: "register-validated",
    component: RegisterValidated,
  },
];
