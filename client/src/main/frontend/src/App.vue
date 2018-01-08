<template>
  <pf-layout id="app" :icons="true">
  <router-link slot="brand" to="/" :exact="true" class="navbar-brand">
    <span class="navbar-brand-name">Vue PatternFly</span>
  </router-link>

  <template slot="vertical-menu">
    <router-link tag="li" class="list-group-item" active-class="active" to="/data" :exact="true">
      <a>
        <span class="fa fa-database" title="Data"></span>
        <span class="list-group-item-value">Data Management</span>
      </a>
    </router-link>
    <router-link tag="li" class="list-group-item" active-class="active" to="/layout" :exact="true">
      <a>
        <span class="fa fa-columns" title="Layout"></span>
        <span class="list-group-item-value">Layout</span>
      </a>
    </router-link>
  </template>

  <router-view></router-view>
</pf-layout>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
