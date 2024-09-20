import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '水果',
    align:"center",
    dataIndex: 'shuiguo'
   },
   {
    title: '订购人',
    align:"center",
    dataIndex: 'dingGouName'
   },
   {
    title: '金额',
    align:"center",
    dataIndex: 'money'
   },
   {
    title: '订单编码',
    align:"center",
    dataIndex: 'code'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '水果',
    field: 'shuiguo',
    component: 'Input',
  },
  {
    label: '订购人',
    field: 'dingGouName',
    component: 'Input',
  },
  {
    label: '金额',
    field: 'money',
    component: 'Input',
  },
  {
    label: '订单编码',
    field: 'code',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
//子表单数据
//子表表格配置
export const fruitOrderPColumns: JVxeColumn[] = [
    {
      title: '水果',
      key: 'shuiguo',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '单价',
      key: 'price',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '合计',
      key: 'heji',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '数量',
      key: 'num',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]


// 高级查询数据
export const superQuerySchema = {
  shuiguo: {title: '水果',order: 0,view: 'text', type: 'string',},
  dingGouName: {title: '订购人',order: 1,view: 'text', type: 'string',},
  money: {title: '金额',order: 2,view: 'text', type: 'string',},
  code: {title: '订单编码',order: 3,view: 'text', type: 'string',},
  //子表高级查询
  fruitOrderP: {
    title: '水果明细',
    view: 'table',
    fields: {
        shuiguo: {title: '水果',order: 0,view: 'text', type: 'string',},
        price: {title: '单价',order: 1,view: 'number', type: 'number',},
        heji: {title: '合计',order: 2,view: 'number', type: 'number',},
        num: {title: '数量',order: 3,view: 'number', type: 'number',},
    }
  },
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
// 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}