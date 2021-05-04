import React, { useRef, useImperativeHandle } from 'react';
import {
	requireNativeComponent,
	UIManager,
	findNodeHandle,
} from 'react-native';

const LeanbackNativeRow = requireNativeComponent('LeanbackRow');

const REQUEST_FOCUS_ACTION = 'request-focus';

const Row = React.forwardRef(
	(
		{
			attributes,
			forbiddenFocusDirections,
			nextFocusUpId,
			nextFocusDownId,
			nextFocusLeftId,
			nextFocusRightId,
			imageTransformationMode,
			showOnlyFocusedInfo,
			data,
			...restOfProps
		},
		ref
	) => {
		const rowRef = useRef();

		// Validate timestamps
		data.forEach((item) => {
			if (
				typeof item.programStartTimestamp === 'string' &&
				item?.programStartTimestamp?.length &&
				item?.programStartTimestamp?.length !== 13
			)
				throw new Error(
					'Timestamp is of incorrect format. Must meet JS standart - miliseconds!'
				);
			if (
				typeof item.programEndTimestamp === 'string' &&
				item?.programStartTimestamp?.length &&
				item?.programEndTimestamp?.length !== 13
			)
				throw new Error(
					'Timestamp is of incorrect format. Must meet JS standart - miliseconds!'
				);
		});

		const attrs = {
			data,
			attributes: {
				width: attributes?.width || 513,
				height: attributes?.height || 176,
				forbiddenFocusDirections:
					forbiddenFocusDirections && Array.isArray(forbiddenFocusDirections)
						? forbiddenFocusDirections
						: [],
				focusedCardAlignment: attributes?.focusedCardAlignment || 'left',
				numberOfRows: attributes?.numberOfRows || 1,
				nextFocusUpId: nextFocusUpId || -1,
				nextFocusDownId: nextFocusDownId || -1,
				nextFocusLeftId: nextFocusLeftId || -1,
				nextFocusRightId: nextFocusRightId || -1,
				cardShape: attributes?.cardShape || 'square',
				borderRadius: attributes?.borderRadius || 0,
				showOnlyFocusedInfo: showOnlyFocusedInfo ?? false,
				hasImageOnly: attributes.hasImageOnly ?? false,
				imageTransformationMode:
					attributes?.imageTransformationMode || 'fitCenter',
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

		return (
			<LeanbackNativeRow
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

export default Row;
