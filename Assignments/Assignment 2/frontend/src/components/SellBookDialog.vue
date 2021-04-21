<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
           Sell Book
        </v-toolbar>
        <v-form>
          <template slot="lead">

          </template>
          <v-text-field v-model="book.name" :disabled=true label="Name" />
          <v-text-field v-model="book.author" :disabled=true label="Author" />
          <v-text-field v-model="book.amount" :disabled=true label="In stock" />
          <v-text-field v-model="book.price" :disabled=true label="Price" />
          <v-text-field v-model="ordered_quantity" font-weight:100 label="Desired quantity"  />
        </v-form>
        <v-card-actions>
          <v-btn @click="sellButton">
            Sell Book
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "SellBookDialog",
  props: {
    book: Object,
    ordered_quantity: Number,
    opened: Boolean,
  },
  methods: {
    sellButton(){
      if(this.ordered_quantity <= this.book.amount){
        api.bookstore.sell(this.book.id, this.ordered_quantity)
            .then(()=> this.$emit("refresh"))
      }
      else {
        this.$alert("There aren't enough books in the stock!")
            .then(() => this.$emit("refresh"));
      }
    },
  },
};
</script>

  .v-text-field >>> input {
  font-size: 0.8em;
  font-weight: 100;
  text-transform: capitalize;
  }
  .v-text-field >>> label {
  font-size: 0.8em;
  }
  .v-text-field >>> button {
  font-size: 0.8em;
  }

<style scoped></style>
