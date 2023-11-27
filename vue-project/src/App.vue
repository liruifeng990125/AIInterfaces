<!-- <template>
  <div class="common-layout" id="app">
    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template> -->


<template>
  
  <div class="common-layout" id="app">
    <el-input placeholder="请输入内容" id="inputdata" v-model="input" clearable>
                      </el-input>
    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside>Aside</el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
    
  </div>
</template>
<script>
    export default {
      data() {
        return {
          input: ''
        }
      }
    }
  </script>


