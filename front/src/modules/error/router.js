export default [
  {
    path: "/error",
    name: "error",
    component: () => import("@error/views/Error")
  },
  {
    path: "/error401",
    name: "error_401",
    component: () => import("@error/views/Error401")
  },
  {
    path: "/error409",
    name: "error_409",
    component: () => import("@error/views/Error409")
  },
  {
    path: "/error500",
    name: "error_500",
    component: () => import("@error/views/Error500")
  },
  {
    path: "/error404",
    name: "error_404",
    component: () => import("@error/views/Error404")
  },
  {
    path: "*",
    name: "error_all",
    component: () => import("@error/views/Error404")
  }
];
