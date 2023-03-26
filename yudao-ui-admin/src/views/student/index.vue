<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="queryParams.age" placeholder="请输入年龄" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="小名" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入小名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="报名日期">
        <el-date-picker v-model="dateRangeOfferDate" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="爸爸姓名" prop="fatherName">
        <el-input v-model="queryParams.fatherName" placeholder="请输入爸爸姓名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="妈妈姓名" prop="motherName">
        <el-input v-model="queryParams.motherName" placeholder="请输入妈妈姓名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_USER_SEX)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['student::create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['student::export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <span>{{ getDictDataLabel(DICT_TYPE.SYSTEM_USER_SEX, scope.row.sex) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="小名" align="center" prop="title" />
      <el-table-column label="生日" align="center" prop="birthDay" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthDay, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="报名日期" align="center" prop="offerDate" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.offerDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="爸爸姓名" align="center" prop="fatherName" />
      <el-table-column label="妈妈姓名" align="center" prop="motherName" />
      <el-table-column label="手机1" align="center" prop="fatherMobile" width="120" />
      <el-table-column label="手机2" align="center" prop="motherMobile" width="120" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['student::update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['student::delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="小名" prop="title">
          <el-input v-model="form.title" placeholder="请输入小名" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_USER_SEX)"
                      :key="dict.value" :label="parseInt(dict.value)">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" @change="ageChange()" />
        </el-form-item>
        <el-form-item label="生日" prop="birthDay">
          <el-date-picker clearable v-model="form.birthDay" type="date" @change="birthChange()" value-format="yyyy-MM-dd" placeholder="选择生日" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="报名日期" prop="offerDate">
          <el-date-picker clearable v-model="form.offerDate" type="date" value-format="yyyy-MM-dd" placeholder="选择报名日期" />
        </el-form-item>
        <el-form-item label="爸爸姓名" prop="fatherName">
          <el-input v-model="form.fatherName" placeholder="请输入爸爸姓名" />
        </el-form-item>
        <el-form-item label="妈妈姓名" prop="motherName">
          <el-input v-model="form.motherName" placeholder="请输入妈妈姓名" />
        </el-form-item>
        <el-form-item label="手机1" prop="fatherMobile">
          <el-input v-model="form.fatherMobile" placeholder="请输入爸爸手机" />
        </el-form-item>
        <el-form-item label="手机2" prop="motherMobile">
          <el-input v-model="form.motherMobile" placeholder="请输入妈妈手机" />
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
import { create, update, deleteStu, get, getPage, exportExcel } from "@/api/student/student";

export default {
  name: "",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 学员管理列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeOfferDate: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: null,
        age: null,
        title: null,
        birthDay: null,
        address: null,
        fatherName: null,
        motherName: null,
        fatherMobile: null,
        motherMobile: null,
        sex: null,
        deleted: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeOfferDate, 'offerDate');
      // 执行查询
      getPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        age: undefined,
        title: undefined,
        birthDay: undefined,
        address: undefined,
        offerDate: undefined,
        fatherName: undefined,
        motherName: undefined,
        fatherMobile: undefined,
        motherMobile: undefined,
        sex: undefined,
        deleted: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeOfferDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学员管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      get(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学员管理";
      });
    },
    ageChange() {
      var date = new Date();
      var birthYear = date.getFullYear() - this.form.age ;
      this.form.birthDay =  new Date(birthYear,1,1);
    },
    birthChange() {
      var date = new Date();
      this.form.age = date.getFullYear() - new Date(this.form.birthDay).getFullYear() ;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          update(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        create(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除学员管理编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteStu(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeOfferDate, 'offerDate');
      // 执行导出
      this.$confirm('是否确认导出所有学员管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportExcel(params);
        }).then(response => {
          this.downloadExcel(response, '学员管理.xls');
        })
    }
  }
};
</script>
