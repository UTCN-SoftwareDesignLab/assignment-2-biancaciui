<template>
  <v-card>
    <v-card-title>
      Books
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn @click="addBook">Add Book</v-btn>
      <v-btn @click="go2usersView">Edit Users</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :books="books"
      :search="search"
      @click:row="editBook"
    ></v-data-table>
    <BookDialog
      :opened="dialogVisible"
      :book="selectedBook"
      @refresh="refreshList"
    ></BookDialog>
  </v-card>
</template>

<script>
import api from "../api";
import BookDialog from "../components/BookDialog";
import router from "@/router";

export default {
  name: "BookList",
  components: { BookDialog },
  data() {
    return {
      books: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Author", value: "author" },
        { text: "Description", value: "description" },
        { text: "Genre", value: "genre" },
        { text: "Number in Stock", value: "amount" },
        { text: "Price", value: "price" },
      ],
      dialogVisible: false,
      selectedBook: {},
    };
  },
  methods: {
    editBook(book) {
      this.selectedBook = book;
      this.dialogVisible = true;
    },
    addBook() {
      this.dialogVisible = true;
    },
    //TODO: add pdfBook and csvBook
    async refreshList() {
      this.dialogVisible = false;
      this.selectedBook = {};
      this.books = await api.books.allBooks();
    },
    go2usersView(){
      router.push("/users");
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
