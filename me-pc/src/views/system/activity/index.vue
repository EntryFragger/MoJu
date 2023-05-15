<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类名称" prop="typeName">
        <el-select v-model="queryParams.typeName" placeholder="请选择分类名称" clearable size="small">
          <el-option
            v-for="dict in typeNameOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:activity:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:activity:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:activity:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="轮播图" align="center" prop="url" >
        <template slot-scope="scope">
            <el-image style="width: 100px; height: 100px" :src="baseUrl+scope.row.url" >
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="分类名称" align="center" prop="typeName">
        <template slot-scope="scope">
          <dict-tag :options="typeNameOptions" :value="scope.row.typeName"/>
        </template>
      </el-table-column>
      <el-table-column label="区域" align="center" prop="provinceCode">
        <template slot-scope="scope">
          {{scope.row.provinceName}}-{{scope.row.cityName}}-{{scope.row.areaName}}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="statusOptions" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <el-table-column label="计划参与人数" align="center" prop="pnum" />
      <el-table-column label="实际参与人数" align="center" prop="num" />
      <el-table-column label="描述" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleCancel(scope.row)"
            v-hasPermi="['system:activity:edit']"
          >取消活动</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:activity:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:activity:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="图片">
          <imageUpload v-model="form.url" :limit="1"/>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="分类名称" prop="typeName">
          <el-select v-model="form.typeName" placeholder="请选择分类名称">
            <el-option
              v-for="dict in typeNameOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择地区" prop="provinceCode">
          <el-select v-model="form.provinceCode" filterable @change="getListC()" placeholder="请选择省">
            <el-option value>请选择</el-option>
            <el-option
              v-for="item in pList"
              :key="item.code"
              :label="item.name"
              :value="item.code"
            ></el-option>
          </el-select>
          <el-select v-model="form.cityCode" filterable @change="getListA()" placeholder="请选择市区">
            <el-option value>请选择</el-option>
            <el-option
              v-for="item in cList"
              :key="item.code"
              :label="item.name"
              :value="item.code"
            ></el-option>
          </el-select>
          
          <el-select v-model="form.areaCode" filterable @change="changeArea" placeholder="请选择区域">
            <el-option value>请选择</el-option>
            <el-option
              v-for="item in aList"
              :key="item.code"
              :label="item.name"
              :value="item.code"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.state" placeholder="请选择状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="计划参与人数" prop="pnum">
          <el-input v-model="form.pnum" type="number" placeholder="请输入计划参与人数" />
        </el-form-item>
        <el-form-item label="实际参与人数" prop="num">
          <el-input v-model="form.num" type="number" placeholder="请输入实际参与人数" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" rows="8" v-model="form.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listActivity, getActivity, delActivity, addActivity, updateActivity, exportActivity } from "@/api/system/activity";
import { listRegion} from "@/api/system/region";

export default {
  name: "Activity",
  data() {
    return {
      baseUrl: this.$serviceUrl,
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 活动表格数据
      activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 分类名称字典
      typeNameOptions: [],
      // 正常,解散,关闭字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        url: null,
        title: null,
        typeName: null,
        state: null,
        pnum: null,
        num: null,
        provinceCode: null,
        provinceName: null,
        cityCode: null,
        cityName: null,
        areaCode: null,
        areaName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      pList:[], //省
      cList:[], //市
      aList: [], //区
      opList:[], //省
      ocList:[], //市
      oaList: [], //区
    };
  },
  created() {
    this.getList();
    this.getDicts("type_name").then(response => {
      this.typeNameOptions = response.data;
    });
    this.getDicts("state").then(response => {
      this.statusOptions = response.data;
    });
    this.getListP();
    this.getListC();
    this.getListA();
  },
  methods: {
    changeArea(){

        for(var i=0; i<this.aList.length; i++){
          if(this.form.areaCode === this.aList[i].code){
              this.form.areaName = this.aList[i].name;
          }
        }
    },
    /**  */
    getListA() {
      if(this.oaList.length === 0){
        let param = {type : 2}
        listRegion(param).then(response => {
          this.aList = response.rows;
          this.oaList = response.rows;
        });
      }
      if(this.form.cityCode){
        
        this.form.areaCode = '';
        this.form.areName = '';

        for(var i=0; i<this.cList.length; i++){
          if(this.form.cityCode === this.cList[i].code){
              this.form.cityName = this.cList[i].name;
          }
        }

        let arr = new Array();
        for(var i=0; i<this.oaList.length; i++){
          if(this.oaList[i].code.startsWith(this.form.cityCode.slice(0, 4))){
            arr.push(this.oaList[i]);
          }
        }
        this.aList = arr;
      }

    },
    /** 查询城市列表 */
    getListC() {
      if(this.ocList.length === 0){
        let param = {type : 1}
        listRegion(param).then(response => {
          this.cList = response.rows;
          this.ocList = response.rows;
        });
      }
      if(this.form.provinceCode){
        this.form.cityCode = '';
        this.form.cityName = '';
        this.form.areaCode = '';
        this.form.areName = '';
        for(var i=0; i<this.pList.length; i++){
          if(this.form.provinceCode === this.pList[i].code){
              this.form.provinceName = this.pList[i].name;
          }
        }
        
        let arr = new Array();
        for(var i=0; i<this.ocList.length; i++){
          if(this.ocList[i].code.startsWith(this.form.provinceCode.slice(0, 2))){
            arr.push(this.ocList[i]);
          }
        }
        this.cList = arr;
      }
    },
    /** 查询省列表 */
    getListP() {
      let param = {type : 0}
      listRegion(param).then(response => {
        this.pList = response.rows;
        this.opList = response.rows;
      });
    },
    /** 查询活动列表 */
    getList() {
      this.loading = true;
      listActivity(this.queryParams).then(response => {
        this.activityList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        url: null,
        title: null,
        typeName: null,
        state: null,
        pnum: null,
        num: null,
        remark: null,
        createTime: null,
        updateTime: null,

        provinceCode: null,
        provinceName: null,
        cityCode: null,
        cityName: null,
        areaCode: null,
        areaName: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动";
    },
    handleCancel(row){

      this.$confirm('是否确认取消活动?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {

          let param = {
            id: row.id,
            state:'解散'
          }
          return updateActivity(param);
        }).then(() => {
          this.getList();
          this.msgSuccess("取消成功");
        }).catch(() => {});
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getActivity(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateActivity(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addActivity(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除活动编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delActivity(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },

  }
};
</script>
