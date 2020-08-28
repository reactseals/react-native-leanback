import React from 'react';
import { requireNativeComponent } from 'react-native';

const LeanbackNativeRow = requireNativeComponent('LeanbackNativeRow');

const Row = React.forwardRef(({ attributes, forbiddenFocusDirections, data, ...restOfProps }, ref) => {
    const attrs = {
        data,
        attributes: {
            width: attributes?.width || 513,
            height: attributes?.height || 176,
            hasTitle: attributes?.hasTitle !== undefined ? attributes.hasTitle : true,
            hasImageOnly: attributes?.hasImageOnly !== undefined ? attributes.hasImageOnly : false,
            hasContent: attributes?.hasContent !== undefined ? attributes.hasContent : true,
            hasIconRight: attributes?.hasIconRight !== undefined ? attributes.hasIconRight : false,
            hasIconLeft: attributes?.hasIconLeft !== undefined ? attributes.hasIconLeft : false,
            forbiddenFocusDirections: forbiddenFocusDirections && Array.isArray(forbiddenFocusDirections) ? forbiddenFocusDirections : [],
        },
    };

    return (
        <LeanbackNativeRow
            {...restOfProps}
            ref={ref}
            dataAndAttributes={attrs}
            onFocus={event => {
                const { item } = event.nativeEvent;
                if (restOfProps.onFocus) restOfProps.onFocus(JSON.parse(item));
            }}
            onClick={event => {
                const { item } = event.nativeEvent;
                if (restOfProps.onClick) restOfProps.onClick(JSON.parse(item));
            }}
        />
    );
});

export default Row;
