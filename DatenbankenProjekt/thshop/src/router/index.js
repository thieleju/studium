import Vue from "vue";
import VueRouter from "vue-router";
import StartView from "@/views/StartView.vue";
import store from "@/store/index";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "start",
    component: StartView,
    meta: {
      title: "Landing Page",
      requiresAuth: false,
    },
  },
  {
    path: "/about",
    name: "about",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
    meta: {
      title: "About",
      requiresAuth: false,
    },
  },
  {
    path: "/home",
    name: "home",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/HomeView.vue"),
    meta: {
      title: "Home",
      requiresAuth: true,
    },
  },
  {
    path: "/profile",
    name: "profile",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ProfileView.vue"),
    meta: {
      title: "Profile",
      requiresAuth: true,
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach(async (to, from, next) => {
  // if meta.requiresAuth is true, check if the user is authenticated
  // only next() if the user is authenticated, otherwise redirect to start
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (store.getters.isAuthenticated) next();
    else next({ name: "start" });
  }
  next();
});

export default router;
