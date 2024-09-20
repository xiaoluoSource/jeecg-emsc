import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '测试名称',
    align: "center",
    dataIndex: 'testName'
  },
  {
    title: '测试时间',
    align: "center",
    dataIndex: 'testTime',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
  },
];

// 高级查询数据
export const superQuerySchema = {
  testName: {title: '测试名称',order: 0,view: 'text', type: 'string',},
  testTime: {title: '测试时间',order: 1,view: 'date', type: 'string',},
};
