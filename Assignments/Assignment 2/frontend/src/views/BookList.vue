<script src="../router/index.js"></script>
<template>
  <v-card>
    <v-card-title>
      Books
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search Book"
        single-line
        hide-details
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn @click="addBook">Add Book</v-btn>
      <v-btn @click="CSVReport">CSV Report</v-btn>
      <v-btn @click="PDFReport">PDF Report</v-btn>
      <v-btn @click="go2usersView">► Users</v-btn>
    </v-card-title>

    <v-data-table
      :headers="headers"
      :items="books"
      :search="search"
      @click:row="editBook"
    ></v-data-table>

      <BookDialog
          :opened="bookDialogVisible"
          :book="selectedBook"
          @refresh="refreshList"
      ></BookDialog>

  </v-card>
</template>

<script>
import api from "../api";
import BookDialog from "../components/BookDialog";
import SellBookDialog from "../components/SellBookDialog";
import router from "@/router";

export default {
  name: "BookList",
  components: { BookDialog, SellBookDialog },
  data() {
    return {
      books: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: true,
          value: "name",
        },
        { text: "Author", value: "author" },
        { text: "Genre", value: "genre" },
        { text: "Description", value: "description" },
        { text: "Number in Stock", value: "amount" },
        { text: "Price", value: "price" },
      ],
      bookDialogVisible: false,
      selectedBook: {},
    };
  },
  methods: {
    editBook(book) {
      this.selectedBook = book;
      this.bookDialogVisible = true;
    },
    addBook() {
      this.bookDialogVisible = true;
    },
    CSVReport(){
      api.books.CSV();
    },
    PDFReport(){
      api.books.PDF();
    },
    async refreshList() {
      this.bookDialogVisible = false;
      this.selectedBook = {};
      this.books = await api.books.findAll();
    },
    go2usersView(){
      router.push("/users");
    },
  },
  computed: {
    isAdmin: function () {
      return this.$store.getters.isAdmin;
    },
  },
  created() {
    this.refreshList();
  },
  mounted() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
