import Error404 from "@error/views/Error404";
import Error401 from "@error/views/Error401";
import Error409 from "@error/views/Error409";
import Error500 from "@error/views/Error500";

export default [
  {
    path: "/error401",
    name: "error_401",
    component: Error401
  },
  {
    path: "/error409",
    name: "error_409",
    component: Error409
  },
  {
    path: "/error500",
    name: "error_500",
    component: Error500
  },
  {
    path: "*",
    name: "error_404",
    component: Error404
  }
];
