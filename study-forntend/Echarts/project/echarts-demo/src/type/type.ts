import * as echarts from 'echarts';
import { InjectionKey } from 'vue';
export const echartsKey: InjectionKey<typeof echarts> = Symbol('echarts');