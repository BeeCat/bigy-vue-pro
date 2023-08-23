<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名" prop="taskName">
        <el-input v-model="queryParams.taskName" placeholder="请输入任务名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="优先级" prop="taskPriority">
        <el-input v-model="queryParams.taskPriority" placeholder="请输入优先级" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="重要紧急" prop="taskImportant">
        <el-input v-model="queryParams.taskImportant" placeholder="请输入重要紧急" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="分配人" prop="assignee">
          <el-select v-model="queryParams.assignee"  filterable placeholder="请输入分配人">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
      </el-form-item>
      <el-form-item label="执行人" prop="receiver">
          <el-select v-model="queryParams.receiver"  filterable placeholder="请输入分配人">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
      </el-form-item>
      <el-form-item label="所属部门" prop="ownerPart">
        <el-input v-model="queryParams.ownerPart" placeholder="请输入所属部门" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="所属项目" prop="ownerProject">
        <el-input v-model="queryParams.ownerProject" placeholder="请输入所属项目" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="是否完成" prop="successFlag">
        <el-input v-model="queryParams.successFlag" placeholder="请输入是否完成" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="审核人" prop="reviewer">
        <el-input v-model="queryParams.reviewer" placeholder="请输入审核人" clearable size="small" @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['zhh:todo-list:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['zhh:todo-list:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="任务名" align="center" prop="taskName" />
      <el-table-column label="任务内容" show-overflow-tooltip=true align="center" prop="taskContent" />
      <el-table-column label="优先级" align="center" prop="taskPriority" >
       <template slot-scope="scope">
        {{ getDictDataLabel(DICT_TYPE.PRIORITY_STAGE_TYPE, scope.row.taskPriority) }}
       </template>
      </el-table-column>
      <el-table-column label="重要紧急" align="center" prop="taskImportant" >
       <template slot-scope="scope">
        {{ getDictDataLabel(DICT_TYPE.IMPORT_STAGE_TYPE, scope.row.taskImportant) }}
       </template>
      </el-table-column>
      <el-table-column label="分配人" align="center" prop="assigneeName" />
      <el-table-column label="任务结束时间"  show-overflow-tooltip=true align="center" prop="deadTime" width="130">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deadTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="执行人" align="center" prop="receiverName" />
      <el-table-column label="计划开始时间" show-overflow-tooltip=true align="center" prop="doStartTime" width="130">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.doStartTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预估时间（h）" align="center" prop="doCostTime" />
      <el-table-column label="所属部门" align="center" prop="ownerPart" />
      <el-table-column label="所属项目" align="center" prop="ownerProject" />
      <el-table-column label="是否完成" align="center" prop="successFlag" >
       <template slot-scope="scope">
        {{ getDictDataLabel(DICT_TYPE.TODO_TASK_STATUS, scope.row.successFlag) }}
       </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="reviewerName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button  v-if="scope.row.successFlag === 1" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['zhh:todo-list:update']">修改</el-button>
          <el-button v-if="scope.row.successFlag === 1" size="mini" type="text" icon="el-icon-edit" @click="handleReceive(scope.row)"
                     v-hasPermi="['zhh:todo-list:update']">认领</el-button>
          <el-button v-if="scope.row.successFlag === 2" size="mini" type="text" icon="el-icon-edit" @click="handleDone(scope.row)"
                     v-hasPermi="['zhh:todo-list:update']">完成</el-button>
          <el-button v-if="scope.row.successFlag === 3" size="mini" type="text" icon="el-icon-edit" @click="handleReview(scope.row)"
                     v-hasPermi="['zhh:todo-list:update']">审验</el-button>
          <el-button v-if="scope.row.successFlag === 4" size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['zhh:todo-list:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="任务内容">
          <editor v-model="form.taskContent" :min-height="92"/>
        </el-form-item>
        <el-form-item label="优先级" prop="taskPriority">
           <el-select v-model="form.taskPriority" placeholder="请输入优先级" clearable size="small">
              <el-option v-for="dict in this.getDictDatas(DICT_TYPE.PRIORITY_STAGE_TYPE)" :key="dict.value" :label="dict.label" :value="dict.value"/>
            </el-select>
        </el-form-item>
        <el-form-item label="重要紧急" prop="taskImportant">
           <el-select v-model="form.taskImportant" placeholder="请输入重要紧急" clearable size="small">
              <el-option v-for="dict in this.getDictDatas(DICT_TYPE.IMPORT_STAGE_TYPE)" :key="dict.value" :label="dict.label" :value="dict.value"/>
            </el-select>
        </el-form-item>
        <el-form-item label="分配人" prop="assignee">
          <el-select v-model="form.assignee"  filterable placeholder="请输入分配人">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="分配时间" prop="assignTime">
          <el-date-picker clearable size="small" v-model="form.assignTime" type="date" value-format="yyyy-MM-dd" placeholder="选择分配时间" />
        </el-form-item>
        <el-form-item label="任务结束时间" prop="deadTime">
          <el-date-picker clearable size="small" v-model="form.deadTime" type="date" value-format="yyyy-MM-dd" placeholder="选择任务结束时间" />
        </el-form-item>
        <el-form-item label="执行人" prop="receiver">
          <el-select v-model="form.receiver"  filterable placeholder="请输入执行人">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="所属部门" prop="ownerPart">
          <el-input v-model="form.ownerPart" placeholder="请输入所属部门" />
        </el-form-item>
        <el-form-item label="所属项目" prop="ownerProject">
          <el-input v-model="form.ownerProject" placeholder="请输入所属项目" />
        </el-form-item>
        <el-form-item label="审核人" prop="reviewer">
          <el-select v-model="form.reviewer"  filterable placeholder="请输入审核人">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 对话框(认领) -->
    <el-dialog :title="title" :visible.sync="openReceive" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="任务名" prop="taskName">
          <el-input v-model="form.taskName" disabled placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="任务内容">
          <editor v-model="form.taskContent" disabled :min-height="92"/>
        </el-form-item>
        <el-form-item label="分派人" prop="taskName">
          <el-input v-model="form.assigneeName" disabled placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="项目" prop="taskName">
          <el-input v-model="form.ownerProject" disabled placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="重要程度" prop="taskName">
          <el-input v-model="form.taskImportant" disabled placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="优先级" prop="taskName">
          <el-input v-model="form.taskPriority" disabled placeholder="请输入任务名" />
        </el-form-item>

        <el-form-item label="执行人" prop="receiver">
          <el-select v-model="form.receiver"  filterable placeholder="请输入执行人">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="计划开始时间" prop="doStartTime">
          <el-date-picker clearable size="small" v-model="form.doStartTime" type="datetime" value-format="yyyy-MM-dd" placeholder="选择计划开始时间" />
        </el-form-item>
        <el-form-item label="需要时间(h)" prop="doCostTime">
          <el-input type="number" v-model="form.doCostTime" placeholder="请输入需要时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancelReceive">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(审核) -->
    <el-dialog :title="title" :visible.sync="openReview" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="任务名" prop="taskName">
          <el-input v-model="form.taskName" disabled placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="任务内容">
          <editor v-model="form.taskContent" disabled :min-height="92"/>
        </el-form-item>
        <el-form-item label="项目" prop="taskName">
          <el-input v-model="form.ownerProject" disabled placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="重要程度" prop="taskName">
          <el-input v-model="form.taskImportant" disabled placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="优先级" prop="taskName">
          <el-input v-model="form.taskPriority" disabled placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="执行人" prop="receiver">
          <el-select v-model="form.receiver" disabled filterable placeholder="请输入执行人">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="计划开始时间" prop="doStartTime">
          <el-date-picker clearable size="small" disabled v-model="form.doStartTime" type="datetime" value-format="yyyy-MM-dd" placeholder="选择计划开始时间" />
        </el-form-item>
        <el-form-item label="需要时间(h)" prop="doCostTime">
          <el-input type="number" disabled v-model="form.doCostTime" placeholder="请输入需要时间" />
        </el-form-item>
        <el-form-item label="审验意见">
          <editor v-model="form.reviewResult" :min-height="92"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancelReview">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(完成) -->
    <el-dialog :title="title" :visible.sync="openDone" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="任务内容">
          <editor v-model="form.taskContent" :min-height="192"/>
        </el-form-item>

        <el-form-item label="执行人" prop="receiver">
          <el-select v-model="form.receiver"  filterable placeholder="请输入执行人">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="执行时间" prop="doEndTime">
          <el-date-picker clearable size="small" v-model="form.doEndTime" type="datetime" value-format="yyyy-MM-dd" placeholder="选择执行时间" />
        </el-form-item>
        <el-form-item label="执行想法" prop="doMind">
          <el-input v-model="form.doMind" placeholder="请输入执行想法" />
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
import { createTodoList, updateTodoList, deleteTodoList, getTodoList, getTodoListPage, exportTodoListExcel } from "@/api/work/todoList";
import Editor from '@/components/Editor';
import { getDictDataLabel, getDictDatas, DICT_TYPE } from '@/utils/dict'
import { listPostUsers } from "@/api/system/user";
import { getPage } from "@/api/student/student";

export default {
  name: "TodoList",
  components: {
    Editor,
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // todolist 待办项列表
      list: [],
      teacherList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openDone: false,
      openReceive: false,
      openReview: false,
      dateRangeAssignTime: [],
      dateRangeDeadTime: [],
      dateRangeDoStartTime: [],
      dateRangeDoEndTime: [],
      dateRangeDoCostTime: [],
      dateRangeReviewTime: [],
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        taskName: null,
        taskContent: null,
        taskPriority: null,
        taskImportant: null,
        assignee: null,
        receiver: null,
        doMind: null,
        ownerPart: null,
        ownerProject: null,
        successFlag: null,
        checkConfirm: null,
        reviewer: null,
        reviewResult: null,
        reviewMind: null,
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
    listPostUsers(8).then(response => {
      this.teacherList = response.data;
    })
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeAssignTime, 'assignTime');
      this.addBeginAndEndTime(params, this.dateRangeDeadTime, 'deadTime');
      this.addBeginAndEndTime(params, this.dateRangeDoStartTime, 'doStartTime');
      this.addBeginAndEndTime(params, this.dateRangeDoEndTime, 'doEndTime');
      this.addBeginAndEndTime(params, this.dateRangeDoCostTime, 'doCostTime');
      this.addBeginAndEndTime(params, this.dateRangeReviewTime, 'reviewTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getTodoListPage(params).then(response => {
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
    /** 取消按钮 */
    cancelReceive() {
      this.openReceive = false;
      this.reset();
    },
    /** 取消按钮 */
    cancelReview() {
      this.openReview = false;
      this.reset();
    },
    /** 取消按钮 */
    cancelDone() {
      this.openDone = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        taskName: undefined,
        taskContent: undefined,
        taskPriority: undefined,
        taskImportant: undefined,
        assignee: undefined,
        assignTime: undefined,
        deadTime: undefined,
        receiver: undefined,
        doStartTime: undefined,
        doEndTime: undefined,
        doCostTime: undefined,
        doMind: undefined,
        ownerPart: undefined,
        ownerProject: undefined,
        successFlag: undefined,
        checkConfirm: undefined,
        reviewer: undefined,
        reviewResult: undefined,
        reviewTime: undefined,
        reviewMind: undefined,
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
      this.dateRangeAssignTime = [];
      this.dateRangeDeadTime = [];
      this.dateRangeDoStartTime = [];
      this.dateRangeDoEndTime = [];
      this.dateRangeDoCostTime = [];
      this.dateRangeReviewTime = [];
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加todolist 待办项";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getTodoList(id).then(response => {
        this.form = response.data;
        this.$set(this.form, "assignTime", new Date(this.form.assignTime))
        this.$set(this.form, "deadTime", new Date(this.form.deadTime))
        this.open = true;
        this.title = "修改todolist 待办项";
      });
    },
    /** 完成按钮操作 */
    handleDone(row) {
      this.reset();
      const id = row.id;
      getTodoList(id).then(response => {
        this.form = response.data;
        this.openDone = true;
        this.form.successFlag = 3
        this.$set(this.form, "doEndTime", new Date(this.form.doEndTime))
        this.title = "修改todolist 待办项";
      });
    },
    /** 认领按钮操作 */
    handleReceive(row) {
      this.reset();
      const id = row.id;
      this.openReceive = true;
      this.form = row
      this.form.successFlag = 2
      this.$set(this.form, "doStartTime", new Date(this.form.doStartTime))
      this.title = "认领todolist 待办项";
    },
    /** 审核验证按钮操作 */
    handleReview(row) {
      this.reset();
      const id = row.id;
      getTodoList(id).then(response => {
        this.form = response.data;
        this.form.successFlag = 4
        this.form.reviewTime = new Date()
        this.$set(this.form, "doEndTime", new Date(this.form.doEndTime))
        this.openReview = true;
        this.$set(this.form, "doStartTime", new Date(this.form.doStartTime))
        this.title = "审验todolist 待办项";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateTodoList(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.openReview = false;
            this.openReceive = false;
            this.openDone = false;
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createTodoList(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除todolist 待办项编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteTodoList(id);
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
      this.addBeginAndEndTime(params, this.dateRangeAssignTime, 'assignTime');
      this.addBeginAndEndTime(params, this.dateRangeDeadTime, 'deadTime');
      this.addBeginAndEndTime(params, this.dateRangeDoStartTime, 'doStartTime');
      this.addBeginAndEndTime(params, this.dateRangeDoEndTime, 'doEndTime');
      this.addBeginAndEndTime(params, this.dateRangeDoCostTime, 'doCostTime');
      this.addBeginAndEndTime(params, this.dateRangeReviewTime, 'reviewTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有todolist 待办项数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTodoListExcel(params);
        }).then(response => {
          this.downloadExcel(response, 'todolist 待办项.xls');
        })
    }
  }
};
</script>
