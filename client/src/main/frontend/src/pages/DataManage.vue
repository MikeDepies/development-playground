<template>
  <div class="cards-pf container-fluid container-cards-pf">
  <div class="row row-cards-pf">
    <div class="col-md-4">
      <pf-card title = "Select a File">
        <vue-clip :options="options">
        <template slot="clip-uploader-action">
          <div>
            <div class="dz-message"><h2> Click or Drag and Drop files here upload </h2></div>
          </div>
        </template>

        <template slot="clip-uploader-body" scope="props">
          <div v-for="file in props.files">
            <img v-bind:src="file.dataUrl" />
            {{ file.name }} {{ file.status }}
          </div>
        </template>
      </vue-clip>
      </pf-card>
    </div>
    <div class="col-md-4">
      <pf-card>
        <form action="/upload" ENCTYPE="multipart/form-data" method="POST" name="wibble">
          choose a file to upload:<input type="file" name="myfile"/><br>
          <input type="submit"/>
      </form>
      </pf-card>
    </div>
    <div class="col-md-4">
      <pf-card></pf-card>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'DataManager',
  data () {
    return {
      options: {
        url: '/upload',
        paramName: 'file'
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
