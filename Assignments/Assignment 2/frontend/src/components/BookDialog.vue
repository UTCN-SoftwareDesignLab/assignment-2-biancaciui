<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNewBook ? "Create book" : "Edit book" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="book.name" label="Name" />
          <v-text-field v-model="book.author" label="Author" />
          <v-text-field v-model="book.genre" label="Genre" />
          <v-text-field v-model="book.description" label="Description" />
          <v-text-field v-model="book.amount" label="Quantity" />
          <v-text-field v-model="book.price" label="Price" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNewBook ? "Create" : "Save" }}
          </v-btn>
          <v-btn v-if="!isNewBook" @click="deleteBook">Delete Book</v-btn>
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
      if (this.isNewBook) {
        api.books
          .create({
            name: this.book.name,
            description: this.book.description,
            author: this.book.author,
            amount: this.book.amount,
            genre: this.book.genre,
            price: this.book.price,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.books
          .edit( this.book.id,
            {
            id: this.book.id,
            name: this.book.name,
            description: this.book.description,
            author: this.book.author,
            amount: this.book.amount,
            genre: this.book.genre,
            price: this.book.price,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deleteBook(){
      api.books.deleteById(this.book.id)
          .then((response) => {
            if (response)
              this.$emit("refresh")
          });
    }
  },
  computed: {
    isNewBook: function () {
      return !this.book.id;
    },
  },
};
</script>

<style scoped></style>
