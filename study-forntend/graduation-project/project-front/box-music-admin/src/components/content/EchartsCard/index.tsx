import React, {
	ForwardedRef,
	useEffect,
	useImperativeHandle,
	useLayoutEffect,
	useRef
} from 'react';
import * as echarts from 'echarts/core';
import { EChartsType } from 'echarts/core';
import { ECElementEvent } from 'echarts/types/src/util/types';
import {
	DatasetComponent,
	DatasetComponentOption,
	DataZoomComponent,
	DataZoomComponentOption,
	GridComponent,
	GridComponentOption,
	LegendComponent,
	LegendComponentOption,
	TitleComponent,
	TitleComponentOption,
	ToolboxComponent,
	ToolboxComponentOption,
	TooltipComponent,
	TooltipComponentOption
} from 'echarts/components';
import {
	BarChart,
	BarSeriesOption,
	LineChart,
	LineSeriesOption,
	PieChart
} from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { SVGRenderer } from 'echarts/renderers';

echarts.use([
	DatasetComponent,
	DataZoomComponent,
	GridComponent,
	LegendComponent,
	TitleComponent,
	ToolboxComponent,
	TooltipComponent,
	LineChart,
	BarChart,
	PieChart,
	UniversalTransition,
	SVGRenderer
]);

export type MyChartOption = echarts.ComposeOption<
	| DatasetComponentOption
	| DataZoomComponentOption
	| GridComponentOption
	| LegendComponentOption
	| TitleComponentOption
	| ToolboxComponentOption
	| TooltipComponentOption
	| LineSeriesOption
	| BarSeriesOption
>;

export interface MyChartProps {
	option: MyChartOption;
	width: number | string;
	height: number | string;
	loading?: boolean;
	onClick?(event: ECElementEvent): any;
}
export interface EChartsCardRef {
	instance(): EChartsType | undefined;
}

const EchartsCardInner: React.ForwardRefRenderFunction<
	EChartsCardRef,
	MyChartProps
> = (
	{ option, width, height, loading = false, onClick },
	ref: ForwardedRef<EChartsCardRef>
) => {
	const echardCard = useRef<HTMLDivElement>(null);
	const cInstance = useRef<EChartsType>();

	// 初始化注册组件，监听 cRef 和 option 变化
	useEffect(() => {
		if (echardCard.current) {
			// 校验 Dom 节点上是否已经挂载了 ECharts 实例，只有未挂载时才初始化
			cInstance.current = echarts.getInstanceByDom(echardCard.current);
			if (!cInstance.current) {
				cInstance.current = echarts.init(echardCard.current, undefined, {
					renderer: 'svg'
				});
			}
			// 设置配置项
			if (option) cInstance.current?.setOption(option);
		}
	}, [echardCard, option]);
	// 监听窗口大小变化重绘
	useEffect(() => {
		window.addEventListener('resize', resize);
		return () => {
			window.removeEventListener('resize', resize);
		};
	}, [option]);

	// 监听高度变化
	useLayoutEffect(() => {
		resize();
	}, [width, height]);

	// 重新适配大小并开启过渡动画
	const resize = () => {
		cInstance.current?.resize({
			animation: { duration: 300 }
		});
	};

	// 展示加载中
	useEffect(() => {
		if (loading) cInstance.current?.showLoading();
		else cInstance.current?.hideLoading();
	}, [loading]);

	useEffect(() => {
		if (echardCard.current) {
			cInstance.current = echarts.getInstanceByDom(echardCard.current);
			if (!cInstance.current) {
				cInstance.current = echarts.init(echardCard.current, undefined, {
					renderer: 'svg'
				});

				// 绑定鼠标点击事件
				cInstance.current.on('click', event => {
					const ec = event as ECElementEvent;
					if (ec && onClick) onClick(ec);
				});
			}

			if (option) cInstance.current?.setOption(option);
		}
	}, [echardCard, option]);
	// 获取实例
	const instance = () => {
		return cInstance.current;
	};

	// 对父组件暴露的方法
	useImperativeHandle(ref, () => ({
		instance
	}));
	return (
		<div className="echards-card" ref={echardCard} style={{ width, height }} />
	);
};

const EchartsCard = React.forwardRef(EchartsCardInner);
export default EchartsCard;
