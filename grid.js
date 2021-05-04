import React, { useRef, useImperativeHandle } from 'react';
import {
	requireNativeComponent,
	UIManager,
	findNodeHandle,
} from 'react-native';

const LeanbackGrid6Col = requireNativeComponent('LeanbackGrid6Col', null);
const LeanbackGrid5Col = requireNativeComponent('LeanbackGrid5Col', null);
const LeanbackGrid4Col = requireNativeComponent('LeanbackGrid4Col', null);

const REQUEST_FOCUS_ACTION = 'request-focus';

const getGridView = (numOfCols) => {
	switch (numOfCols) {
		case 6:
			return LeanbackGrid6Col;
		case 5:
			return LeanbackGrid5Col;
		default:
			return LeanbackGrid4Col;
	}
};

const Grid = React.forwardRef(
	(
		{
			attributes,
			forbiddenFocusDirections,
			showOnlyFocusedInfo,
			nextFocusUpId,
			nextFocusDownId,
			nextFocusLeftId,
			nextFocusRightId,
			data,
			numOfCols,
			...restOfProps
		},
		ref
	) => {
		const rowRef = useRef();

		const attrs = {
			data,
			attributes: {
				width: attributes?.width || 513,
				height: attributes?.height || 176,
				forbiddenFocusDirections:
					forbiddenFocusDirections && Array.isArray(forbiddenFocusDirections)
						? forbiddenFocusDirections
						: [],
				nextFocusUpId: nextFocusUpId || -1,
				nextFocusDownId: nextFocusDownId || -1,
				nextFocusLeftId: nextFocusLeftId || -1,
				nextFocusRightId: nextFocusRightId || -1,
				cardShape: attributes?.cardShape || 'square',
				borderRadius: 0,
				showOnlyFocusedInfo: showOnlyFocusedInfo ?? false,
				hasImageOnly: attributes.hasImageOnly ?? false,
			},
		};

		useImperativeHandle(ref, () => ({
			requestFocus: () => {
				requestFocus();
			},
		}));

		const requestFocus = () => {
			const node = findNodeHandle(rowRef.current);
			UIManager.dispatchViewManagerCommand(node, REQUEST_FOCUS_ACTION, []);
		};

		const GridView = getGridView(numOfCols);

		return (
			<GridView
				{...restOfProps}
				ref={rowRef}
				dataAndAttributes={attrs}
				onFocus={(event) => {
					const { item } = event.nativeEvent;
					if (restOfProps.onFocus) restOfProps.onFocus(JSON.parse(item));
				}}
				onPress={(event) => {
					const { item } = event.nativeEvent;
					if (restOfProps.onPress) restOfProps.onPress(JSON.parse(item));
				}}
				onDataIdsReady={(event) => {
					const { data: stringifiedData } = event.nativeEvent;
					if (restOfProps.onDataIdsReady) {
						const data = JSON.parse(stringifiedData);
						if (data.length) restOfProps.onDataIdsReady(data);
					}
				}}
			/>
		);
	}
);

export default Grid;
