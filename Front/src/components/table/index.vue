<template>
	<div class="table">
		<el-table :data="tableData">
			<el-table-column prop="id" label="序号" width="140">
			</el-table-column>
			<el-table-column prop="username" label="姓名" width="220">
			</el-table-column>
			<el-table-column prop="select_3" :label="title">
				<template slot-scope="scope">
					<el-dropdown trigger="click" :disabled='scope.row.isUpdata' @command="handleCommand">
						<span class="el-dropdown-link">
						{{scope.row.select_3}}<i class="el-icon-arrow-down el-icon--right"></i>
						</span>
						<el-dropdown-menu slot="dropdown">
							<el-dropdown-item :command="beforeHandleCommand(scope.$index, item)" v-for="(item,index) in optionData">{{item.title}}</el-dropdown-item>								
						</el-dropdown-menu>
					</el-dropdown>
				</template>					
			</el-table-column>
			<el-table-column
				fixed="right"
				label="操作"
				width="200">
				  <template slot-scope="scope">
					<el-button
					  size="mini"
					  @click="handleEdit(scope.$index)">修改</el-button>						
					<el-button
					  size="mini"
					  type="primary"
					 :disabled='scope.row.isUpdata'
					  @click="handlesubmit(scope.$index)">确定</el-button>							  
				  </template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default {
		props: ['tableData','optionData','title'],
		data(){
			return {
				isUpdata: -1,
			}
		},
		methods: {
			beforeHandleCommand(index, command) { // 下拉框传递多个值
			  return {
			     'dataIndex': index,
			     'command': command
			  }
			},
			handleCommand(item) {								
				this.tableData[item.dataIndex].select_3_id = item.command.id;
				this.tableData[item.dataIndex].select_3 = item.command.title;				
			},
			handleEdit(index,l){				
				if(this.isUpdata != -1){					
					this.tableData[this.isUpdata].isUpdata = true;
				}				
				this.tableData[index].isUpdata = false;
								
				this.isUpdata = index;						
			},
			handlesubmit(index){				
				this.$emit("getTableItem",this.tableData[index]);				
				this.tableData[index].isUpdata = true;
			}
		}
	}
</script>

<style>
</style>