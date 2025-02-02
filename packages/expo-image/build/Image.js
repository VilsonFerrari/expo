import React from 'react';
import { StyleSheet, } from 'react-native';
import ExpoImage from './ExpoImage';
const DEFAULT_RESIZE_MODE = 'cover';
export default class Image extends React.Component {
    static getDerivedStateFromProps(props) {
        return {
            onLoad: props.onLoadEnd
                ? e => {
                    if (props.onLoad) {
                        props.onLoad(e);
                    }
                    props.onLoadEnd();
                }
                : props.onLoad,
            onError: props.onLoadEnd
                ? e => {
                    if (props.onError) {
                        props.onError(e);
                    }
                    props.onLoadEnd();
                }
                : props.onError,
        };
    }
    state = {
        onLoad: undefined,
        onError: undefined,
    };
    render() {
        const { style, resizeMode: resizeModeProp, ...restProps } = this.props;
        const { resizeMode: resizeModeStyle, ...restStyle } = StyleSheet.flatten([style]) || {};
        const resizeMode = resizeModeProp ?? resizeModeStyle ?? DEFAULT_RESIZE_MODE;
        return (React.createElement(ExpoImage, { ...restProps, style: restStyle, resizeMode: resizeMode, onLoad: this.state.onLoad, onError: this.state.onError }));
    }
}
//# sourceMappingURL=Image.js.map