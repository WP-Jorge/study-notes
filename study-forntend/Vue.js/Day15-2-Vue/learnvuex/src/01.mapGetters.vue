<template>
</template>

<script>
  import {mapActions} from 'vuex';
  import {mapGetters} from 'vuex';
  export default {
    name: "MapGetter",
    methods: {
      ...mapActions // 用法跟mapGetters一样
    },
    computed: {
      // 1.将所有的getters里面的计算属性映射到这里

      // 2.将指定计算属性映射到这里
      ...mapGetters(['powerCounter', 'overTwenty'])

      // 3.使用对象
      ...mapGetters({
        p: 'powerCounter',
        o: 'overTwenty'
      })
    }
  }
</script>

<style>
</style>
