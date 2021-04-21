<template>
  <v-card>
    <v-card-title>
      Book Store
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>

    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="books"
        @click:row="sellBook"
        :search="search"
    ></v-data-table>

    <SellBookDialog
        :opened="dialogVisible"
        :book="selectedBook"
        @refresh="refreshList"
    ></SellBookDialog>
  </v-card>
</template>

<script>
import api from "../api";
import SellBookDialog from "../components/SellBookDialog";
export default {
  name: "SellBook",
  components: { SellBookDialog },
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
        { text: "Price", value: "price" },
        { text: "Quantity", value: "amount" },
      ],
      dialogVisible: false,
      selectedBook: {},
    };
  },
  methods: {
    filter(){
      this.books = api.books.filterByDescription(this.search);
    },
    sellBook(book) {
      this.selectedBook = book;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedBook = {};
      this.books = await api.books.findAll();
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