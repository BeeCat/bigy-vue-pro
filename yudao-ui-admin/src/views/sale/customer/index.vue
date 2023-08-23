<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="小名" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入小名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="来源" prop="sourceFrom">
          <el-input v-model="queryParams.sourceFrom" placeholder="请输入来源" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="来源销售" prop="saleFrom">
        <el-select v-model="queryParams.saleFrom" filterable placeholder="请选择跟踪老师">
          <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="电话" prop="fatherMobile">
        <el-select v-model="queryParams.fatherMobile" @change="getList" filterable clearable placeholder="请输入电话">
           <el-option v-for="item in queryCustomerList" :key="item.fatherMobile" :label="item.fatherMobile" :value="item.fatherMobile">
           </el-option>
         </el-select>
      </el-form-item>
      <el-form-item label="学校" prop="school">
        <el-input v-model="queryParams.school" placeholder="请输入学校" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="客户等级" prop="gradeType">
        <el-select v-model="queryParams.gradeType" placeholder="请选择客户等级" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.CUSTOMER_GRADE_TYPE)"
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
                   v-hasPermi="['zhh:customer:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['zhh:customer:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>
    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="小名" align="center" prop="title" />
      <el-table-column label="来源" align="center" prop="sourceFromName" />
      <el-table-column label="来源销售" align="center" prop="saleFromName" />
      <el-table-column label="客户等级" align="center" prop="gradeType" />
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="生日" align="center" prop="birthDay" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthDay, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="学校" align="center" prop="school" />
      <el-table-column label="操作" align="center" width='218px' class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['zhh:customer:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleTrackAdd(scope.row)"
                     v-hasPermi="['zhh:customer:connect']">跟踪</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleTrackList(scope.row)"
                     v-hasPermi="['zhh:customer:connect']">跟踪记录</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleBuyCourse(scope.row)"
                     v-hasPermi="['zhh:customer:connect']">购课</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleInvite(scope.row)"
                     v-hasPermi="['zhh:customer:connect']">约试听</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['zhh:customer:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 对话框(跟踪记录) -->
    <el-dialog :title="title" :visible.sync="trackListOpen" width="1200px" append-to-body>
        <!-- 列表 -->
        <el-table v-loading="loading" :data="trackList">
          <el-table-column label="" align="center" prop="id" />
          <el-table-column label="客户id" align="center" prop="customerId" />
          <el-table-column label="跟踪老师" align="center" prop="trackTeacher" />
          <el-table-column label="跟踪内容" align="center" prop="trackContent" />
          <el-table-column label="跟踪结果" align="center" prop="trackResult" />
          <el-table-column label="沟通前等级" align="center" prop="beforeCustomerGrade" />
          <el-table-column label="沟通后等级" align="center" prop="afterCustomerGrade" />
          <el-table-column label="沟通经验心得" align="center" prop="trackExperience" />
          <el-table-column label="等级是否变化" align="center" prop="gradeChangeFlag" />
          <el-table-column label="是否邀约成功" align="center" prop="inviteFlag" />
          <el-table-column label="创建时间" align="center" prop="createTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
        </el-table>

      <div slot="footer" class="dialog-footer">
        <el-button @click="trackListCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(沟通) -->
    <el-dialog :title="title" :visible.sync="trackOpen" width="500px" append-to-body>
      <el-form ref="trackForm" :model="trackForm" label-width="80px">

        <el-form-item label="客户" prop="customerId">
          <el-input v-model="trackCustomer.name" disabled placeholder="请输入客户id" />
        </el-form-item>
        <el-form-item label="跟踪老师" prop="trackTeacher">
          <el-select v-model="trackForm.trackTeacher" filterable placeholder="请选择跟踪老师">
            <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="沟通等级" prop="afterCustomerGrade">
           <el-select v-model="trackForm.gradeType" placeholder="请选择客户等级">
               <el-option v-for="dict in this.getDictDatas(DICT_TYPE.CUSTOMER_GRADE_TYPE)"
                                     :key="dict.value" :label="dict.label" :value="dict.value"/>
            </el-select>
        </el-form-item>
        <el-form-item label="沟通心得" prop="trackExperience">
          <el-input v-model="trackForm.trackExperience" placeholder="请输入沟通经验心得" />
        </el-form-item>
        <el-form-item label="跟踪内容">
          <editor v-model="trackForm.trackContent" :min-height="192"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitTrackForm">确 定</el-button>
        <el-button @click="trackCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(邀约) -->
    <el-dialog :title="title" :visible.sync="inviteOpen" width="500px" append-to-body>
      <el-form ref="inviteForm" :model="inviteForm" label-width="100px">
        <el-form-item label="客户" prop="customerId">
          <el-input v-model="trackCustomer.name" disabled placeholder="请输入客户id" />
        </el-form-item>
        <el-form-item label="邀约老师" prop="trackTeacher">
          <el-select v-model="inviteForm.trackTeacher" filterable placeholder="请选择邀约老师">
            <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="试听老师" prop="inviteTeacher">
           <el-select v-model="inviteForm.inviteTeacher" filterable placeholder="请选择邀约老师">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="试听班级" prop="inviteClass">
           <el-select v-model="inviteForm.inviteClass" placeholder="请选试听班级">
               <el-option v-for="item in courseClassList" :key="item.id" :label="item.name" :value="item.id"/>
            </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitInviteForm">确 定</el-button>
        <el-button @click="inviteCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(购课) -->
    <el-dialog :title="title" :visible.sync="buyCourseOpen" width="500px" append-to-body>
      <el-form ref="buyCourseForm" :model="buyCourseForm" label-width="120px">
        <el-form-item label="客户" prop="customerId">
          <el-input v-model="buyCourseCustomer.name" disabled placeholder="请输入客户id" />
        </el-form-item>
        <el-form-item label="邀约老师" prop="trackTeacher">
          <el-select v-model="buyCourseForm.trackTeacher" filterable placeholder="请选择邀约老师">
            <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="试听老师" prop="classTeacher">
           <el-select v-model="buyCourseForm.classTeacher" @change="inviteTeacherChange()" filterable placeholder="请选择邀约老师">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="试听班级" prop="courseClass">
           <el-select v-model="buyCourseForm.courseClass" placeholder="请选试听班级">
               <el-option v-for="item in courseClassList"
                                     :key="item.id" :label="item.name" :value="item.id"/>
            </el-select>
        </el-form-item>
        <el-form-item label="购买课程" prop="course">
           <el-select v-model="buyCourseForm.course" placeholder="请选购买课程">
               <el-option v-for="item in courseList"
                                     :key="item.id" :label="item.courseName" :value="item.id"/>
            </el-select>
        </el-form-item>
      <el-form-item label="购买课包" prop="coursePackage">
         <el-select v-model="buyCourseForm.coursePackage" placeholder="请选购买课程">
             <el-option v-for="item in coursePackageList"
                                   :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
      </el-form-item>
       <el-form-item label="购买课程数" prop="customerId">
          <el-input v-model="buyCourseForm.buyCourseNum"  placeholder="请输入购买课程数" />
        </el-form-item>
       <el-form-item label="支付金额（元）" prop="coursePrice">
          <el-input v-model="buyCourseForm.coursePrice"  placeholder="请输入支付金额" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitBuyCourseForm">确 定</el-button>
        <el-button @click="buyCourseCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(邀约) -->
    <el-dialog :title="title" :visible.sync="inviteOpen" width="500px" append-to-body>
      <el-form ref="inviteForm" :model="inviteForm" label-width="80px">
        <el-form-item label="客户" prop="customerId">
          <el-input v-model="trackCustomer.name" disabled placeholder="请输入客户id" />
        </el-form-item>
        <el-form-item label="邀约老师" prop="trackTeacher">
          <el-select v-model="inviteForm.trackTeacher" filterable placeholder="请选择邀约老师">
            <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上课老师" prop="inviteTeacher">
           <el-select v-model="inviteForm.inviteTeacher" @change="inviteTeacherChange()" filterable placeholder="请选择邀约老师">
             <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
             </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="试听班级" prop="inviteClass">
           <el-select v-model="inviteForm.inviteClass" placeholder="请选试听班级">
               <el-option v-for="item in courseClassList" :key="item.id" :label="item.name" :value="item.id"/>
            </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitInviteForm">确 定</el-button>
        <el-button @click="inviteCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="小名" prop="title">
          <el-input v-model="form.title" placeholder="请输入小名" />
        </el-form-item>

      <el-form-item label="来源类型" prop="sourceFromType">
        <el-radio-group v-model="sourceFromType">
          <el-radio :label="1">活动</el-radio>
          <el-radio :label="2">自定义</el-radio>
        </el-radio-group>
      </el-form-item>
        <el-form-item label="来源" prop="sourceFrom">
          <el-input v-if="sourceFromType == 2" v-model="form.sourceFrom" placeholder="请输入来源" />
          <el-select v-else v-model="form.sourceFrom" placeholder="请选择来源" clearable size="small">
            <el-option v-for="form in activityList" :key="form.id" :label="form.name" :value="form.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="来源销售" prop="saleFrom">
          <el-select v-model="form.saleFrom" filterable placeholder="请选择">
            <el-option v-for="item in teacherList" :key="item.id" :label="item.nickname" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客户等级" prop="gradeType">
          <el-select v-model="form.gradeType" placeholder="请选择客户等级">
             <el-option v-for="dict in this.getDictDatas(DICT_TYPE.CUSTOMER_GRADE_TYPE)"
                                   :key="dict.value" :label="dict.label" :value="dict.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="学校" prop="school">
          <el-input v-model="form.school" placeholder="请输入学校" />
        </el-form-item>
        <el-form-item label="小区" prop="village">
         <el-select v-model="form.village" placeholder="请选择小区">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.CUSTOMER_VILLAGE)"
                                  :key="dict.value" :label="dict.label" :value="dict.value"/>
         </el-select>
        </el-form-item>
        <el-form-item label="家长姓名" prop="fatherName">
          <el-input v-model="form.fatherName" placeholder="请输入家长姓名" />
        </el-form-item>
        <el-form-item label="家长电话" prop="motherMobile">
          <el-input v-model="form.fatherMobile" placeholder="请输入家长电话" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="生日" prop="birthDay">
          <el-date-picker clearable v-model="form.birthDay" type="date" value-format="yyyy-MM-dd" placeholder="选择生日" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
               <el-radio :label="1">男</el-radio>
               <el-radio :label="2">女</el-radio>
             </el-radio-group>
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
import { createCustomer, updateCustomer, deleteCustomer, getCustomer, getCustomerPage, exportCustomerExcel } from "@/api/sale/customer";
import { getActivityPage } from "@/api/sale/activity";
import { createCustomerTrack, createCustomerInvite, updateCustomerTrack, getCustomerTrack, getCustomerTrackPage } from "@/api/sale/customerTrack";
import { getCourseClassPage } from "@/api/education/courseClass";
import { getCoursePackagePage } from "@/api/account/coursePackage";
import { getCoursePage } from "@/api/education/course";
import { listPostUsers } from "@/api/system/user";
import { createStudentAccount, createBuyCourse, getStudentAccountPage } from "@/api/account/studentAccount";
import Editor from '@/components/Editor';


export default {
  name: "Customer",
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
      // 列表
      list: [],
      trackList: [],
      // 弹出层标题
      title: "",
      sourceFromType: 1,
      // 是否显示弹出层
      open: false,
      trackOpen: false,
      inviteOpen: false,
      trackListOpen: false,
      buyCourseOpen: false,
      dateRangeOfferDate: [],
      dateRangeCreateTime: [],
      activityList: [],
      teacherList: [],
      courseClassList: [],
      queryCustomerList: [],
      courseList: [],
      coursePackageList: [],
      teacherMap: {},
      trackCustomer:{},
      buyCourseCustomer:{},
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: null,
        title: null,
        sourceFrom: null,
        teacherFrom: null,
        saleFrom: null,
        age: null,
        birthDay: null,
        school: null,
        village: null,
        address: null,
        fatherName: null,
        motherName: null,
        fatherMobile: null,
        motherMobile: null,
        gradeType: null,
        remark: null,
        sex: null,
      },
      // 表单参数
      form: {},
      trackForm: {},
      inviteForm: {},
      buyCourseForm: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getQueryCustomerList()
    listPostUsers(8).then(response => {
      this.teacherList = response.data;
      for(var index in this.teacherList ) {
        this.teacherMap[this.teacherList[index].id] = this.teacherList[index]
      }
    })
    getActivityPage({pageSize:100}).then(response => {
      this.activityList = response.data.list
      for(var index in this.activityList ) {
        this.activityMap[this.activityList[index].id] = this.activityList[index]
      }
    })
    getCoursePage({pageSize:100}).then(response => {
      this.courseList = response.data.list
    })

  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeOfferDate, 'offerDate');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getCustomerPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    getQueryCustomerList() {
          // 执行查询
          getCustomerPage({pageSize:1000}).then(response => {
            this.queryCustomerList = response.data.list;
          });
    },
    inviteTeacherChange(row) {
      getCourseClassPage({teacherCode: this.inviteForm.inviteTeacher, pageSize:100}).then(response => {
        this.courseClassList = response.data.list
      })
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 取消按钮 */
    inviteCancel() {
      this.inviteOpen = false;
      this.inviteReset();
    },
    /** 取消按钮 */
    trackCancel() {
      this.trackOpen = false;
      this.trackReset();
    },
    /** 取消按钮 */
    buyCourseCancel() {
      this.buyCourseOpen = false;
      this.buyCourseReset();
    },
    /** 取消按钮 */
    trackListCancel() {
      this.trackListOpen = false;
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        title: undefined,
        sourceFrom: undefined,
        teacherFrom: undefined,
        saleFrom: undefined,
        age: undefined,
        birthDay: undefined,
        offerDate: undefined,
        school: undefined,
        village: undefined,
        address: undefined,
        fatherName: undefined,
        motherName: undefined,
        fatherMobile: undefined,
        motherMobile: undefined,
        gradeType: undefined,
        remark: undefined,
        sex: undefined,
      };
      this.resetForm("form");
    },
    /** 表单重置 */
    trackReset() {
      this.trackForm = {
        id: undefined,
        name: undefined,
        trackTeacher: undefined,
        afterCustomerGrade: undefined,
        customerId: undefined,
        trackExperience: undefined,
        trackContent: undefined,
      };
      this.resetForm("form");
    },
    /** 表单重置 */
    inviteReset() {
      this.inviteForm = {
        id: undefined,
        name: undefined,
        trackTeacher: undefined,
        afterCustomerGrade: undefined,
        customerId: undefined,
        trackExperience: undefined,
        trackContent: undefined,
      };
      this.resetForm("inviteForm");
    },
    /** 表单重置 */
    buyCourseReset() {
      this.buyCourseForm = {
        id: undefined,
        name: undefined,
        trackTeacher: undefined,
        afterCustomerGrade: undefined,
        customerId: undefined,
        trackExperience: undefined,
        trackContent: undefined,
      };
      this.resetForm("buyCourseForm");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeOfferDate = [];
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加";
    },
    /** 新增按钮操作 */
    handleTrackAdd(row) {
      this.trackReset();
      this.trackCustomer = row
      this.trackForm.customerId = row.id

      this.trackOpen = true;
      this.title = "添加跟踪";
    },
    /** 新增按钮操作 */
    handleBuyCourse(row) {
      this.buyCourseReset();
      this.buyCourseCustomer = row
      this.buyCourseForm.customerId = row.id

      getCoursePackagePage({pageSize:100}).then(response => {
        this.coursePackageList = response.data.list;
      })

      this.buyCourseOpen = true;
      this.title = "购买课程";
    },
    /** 新增按钮操作 */
    handleInvite(row) {
      this.inviteReset();
      this.trackCustomer = row
      this.inviteForm.customerId = row.id

      this.inviteOpen = true;
      this.title = "约试听";
    },
    /** 新增按钮操作 */
    handleTrackList(row) {
      this.reset();
      this.trackCustomer = row

      getCustomerTrackPage({customerId:row.id}).then(response => {
        this.trackList = response.data.list;
      })

      this.trackListOpen = true;
      this.title = "跟踪记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCustomer(id).then(response => {
        this.form = response.data;

        this.$set(this.form, "birthDay", new Date(this.form.birthDay))
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitInviteForm() {
      this.$refs["inviteForm"].validate(valid => {
        if (!valid) {
          return;
        }

        // 添加的提交
        createCustomerInvite(this.inviteForm).then(response => {
          this.msgSuccess("邀约操作成功");
          this.inviteOpen = false;
        });
      });
    },
    /** 提交按钮 */
    submitBuyCourseForm() {
      this.$refs["buyCourseForm"].validate(valid => {
        if (!valid) {
          return;
        }
        // 添加的提交
        createBuyCourse(this.buyCourseForm).then(response => {
          this.msgSuccess("邀约操作成功");
          this.buyCourseOpen = false;
        });
      });
    },
    /** 提交按钮 */
    submitTrackForm() {
      this.$refs["trackForm"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.trackForm.id != null) {
          updateCustomerTrack(this.trackForm).then(response => {
            this.msgSuccess("修改成功");
            this.trackOpen = false;
          });
          return;
        }
        // 添加的提交
        createCustomerTrack(this.trackForm).then(response => {
          this.msgSuccess("新增成功");
          this.trackOpen = false;
        });
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
          updateCustomer(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCustomer(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteCustomer(id);
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
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCustomerExcel(params);
        }).then(response => {
          this.downloadExcel(response, '.xls');
        })
    }
  }
};
</script>
