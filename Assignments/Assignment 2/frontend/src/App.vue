<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <TopBar v-if="isLoggedIn"></TopBar>
<!--        <Admin v-if="isLoggedIn && isAdmin"> </Admin>-->
<!--        <Employee  v-if="isLoggedIn && !isAdmin"></Employee>-->
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>

import TopBar from "./components/TopBar";

export default {
  name: "App",
  components: {
    TopBar,
  },
  data: () => ({
    //
  }),
  computed: {
    isLoggedIn: function () {
      return this.$store.state.auth.status.loggedIn;
    },
    // isAdmin: function () {
    //   return this.$store.getters.isAdmin;
    // },
  },
};
</script>
