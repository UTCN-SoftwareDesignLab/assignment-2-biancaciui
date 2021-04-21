import Vue from "vue";
import VueRouter from "vue-router";

import UserList from "../views/UserList.vue";//ADMIN
import BookList from "../views/BookList.vue";//ADMIN
import SellBook from "../views/SellBook.vue";//EMPLOYEE
import Login from "../views/Login";

import { auth as store } from "../store/auth.module";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  { //ADMIN
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
        console.log("INDEX ADMIN");
      } else {
        console.log("INDEX EMPLOYEE");
        next({ name: "SellBook" });
      }
    },
  },
  { //ADMIN
    path: "/books",
    name: "Books",
    component: BookList,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "SellBook" });
      }
    },
  },
  {
    path: "/bookstore",
    name: "SellBook",
    component: SellBook,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        console.log("BOOK STORE CREATED");
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },

  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
