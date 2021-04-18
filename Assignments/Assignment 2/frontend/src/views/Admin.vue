<template>
  <div id="admin">
    <h1>Employee</h1>
    <div id="nav">
      <router-link to="../BookList">Books</router-link> |
      <router-link to="../UserList">Employees</router-link> |
    </div>
    <b-container>
      <router-view/>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "Admin.vue"
}
</script>

<style scoped>

</style>