<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create book" : "Edit book" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="book.name" label="Name" />
          <v-text-field v-model="book.description" label="Description" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "BookDialog",
  props: {
    book: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.books
          .create({
            name: this.book.name,
            description: this.book.description,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.books
          .edit({
            id: this.book.id,
            name: this.book.name,
            description: this.book.description,
          })
          .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.book.id;
    },
  },
};
</script>

<style scoped></style>
