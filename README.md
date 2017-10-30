# VRIX_ANDROID

[![License][license-image]][license-url]
[![Platform](https://img.shields.io/cocoapods/p/LFAlertController.svg?style=flat)](http://cocoapods.org/pods/LFAlertController)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

VMAP, VAST VRiX

## Features

- [x] VMAP, VAST supported.
- [x] Preroll, Midroll, Postroll 광고 지원
- [x] linear, nonlinear 지원

## Requirements

- iOS 8.0+
- Xcode 8.3

## Installation

#### Manually
1. Download and drop ```VRiX.framework``` in your project.  
2. Project > Build settings > Other linker flasg '''-Objc''' '''-all_load'''  

## Usage example

#### init
```objc
#import <VRiX/VRiXManager.h>

- (void)viewDidLoad {
    [super viewDidLoad];
    self.vrixMananger = [[VRiXManager alloc] initWithKey:VRIX_KEY hashKey:VRIX_HASHKEY];

    [self.vrixMananger fetchVRiX:[NSURL URLWithString:encodedUrl]
               completionHandler:^(BOOL success, NSError *error){
                    //
                    if (success == YES){
                        [self playPreroll];
                    }else{
                        //TODO: error handler
                    }
                }];
}
```
#### Play AD
1. Preroll & OutStream

```objc
- (void) playPreroll
{
    NSInteger numberOfPreroll = [_vrixMananger prerollCount];
    if (numberOfPreroll > 0){
        // Play Preroll
        [_vrixMananger prerollAtView:_adView completionHandler:^{
            //TODO: preroll광고 끝난후에 처리할 내용을 구현
        }];
    }
}
```
2. Midroll
```objc
- (void) playMidroll
{
    CGFloat currentTime = CMTimeGetSeconds(_player.currentTime);

    //vrix midroll handling
    if([_vrixMananger midrollCount] > 0){
        // Play Midroll
        [_vrixMananger midrollAtView:_adView
                          timeOffset:currentTime
                     progressHandler:^(BOOL start, GXAdBreakType breakType, NSAttributedString *message){
                            //
                            if (message != nil && breakType == GXAdBreakTypelinear){
                                //TODO: show message
                            }

                            if (start == YES){
                                //TODO: 광고가 시작되었을때 처리
                            }
                
                    }
                    completionHandler:^(GXAdBreakType breakType){
                            //TODO: midroll광고가 완료되었때 처리 
                    }];
                }
        }];
    }
}
```

3. Postroll
```objc
- (void) playpostroll
{
    NSInteger numberOfPostroll = [_vrixMananger postrollCount];
    if (numberOfPostroll > 0){
        [_vrixMananger postrollAtView:_adView completionHandler:^{
            //TODO:postroll광고 끝난후에 처리할 내용을 구현
        }];
}
```
4. Stop AD
```objc
[self.vrixMananger stopCurrentAD];
```

#### VRiX Handling methods
```objc
/*!
@method			initWithKey:hashKey:
@param          key 사용자별 키값
@param			hashKey 사용자별 시크릿 키값
@discussion		브릭스를 핸들링가능한 메니져를 init한다.
*/
- (VRiXManager *) initWithKey:(NSString *)key hashKey:(NSString *)hashKey;

/*!
@method			fetchVRiX:completionHandler:
@param				url VRiX주소
@param				handler fetch 완료 호출될 block
@discussion		브릭스서비스에서 광고 정보를 fetch한다..
*/
- (void) fetchVRiX:(NSURL *)url
completionHandler:(void (^)(BOOL success, NSError *error))handler;

/*!
@method			stopCurrentAD
@discussion		현재 재생중인 광고를 중지 시킨다.
*/
- (void) stopCurrentAD;

/*!
@method			prerollAtView:completionHandler
@param				targetView 광고가 재생될 뷰
@param				handler 광고재생 완료 후 호출될 block
@discussion		프리롤 광고를 해당뷰에 재생시킨다.
*/
- (void) prerollAtView:(UIView *)targetView
completionHandler:(void (^)(void))handler;

/*!
@method			prerollCount
@discussion		프리롤광고의 곗수를 리턴한다.
*/
- (NSInteger) prerollCount;

/*!
@method			midrollAtView:timeOffset:progressHandler:completionHandler
@param				targetView 광고가 재생될 뷰
@param             offset 현재 재생중인 컨텐츠의 재생시간
@param             progressHandler timeOffset에 따른 결과값 block 코드
@param				completionHandler 광고재생 완료 후 호출될 block
@discussion		미드롤 광고를 해당뷰에 재생한다.
*/
- (void) midrollAtView:(UIView *)targetView
timeOffset:(NSTimeInterval)offset
progressHandler:(void (^)(BOOL whenItStart, GXAdBreakType breakType, NSAttributedString *message))progressHandler
completionHandler:(void (^)(GXAdBreakType breakType))completionHandler;

/*!
@method			midrollCount
@discussion		미드롤광고의 곗수를 리턴한다.
*/
- (NSInteger) midrollCount;

/*!
@method			postrollAtView:completionHandler
@param				targetView 광고가 재생될 뷰
@param				handler 광고재생 완료 후 호출될 block
@discussion		포스트롤 광고를 해당뷰에 재생시킨다.
*/
- (void) postrollAtView:(UIView *)targetView
completionHandler:(void (^)(void))handler;

/*!
@method			postrollCount
@discussion		포스트롤광고의 곗수를 리턴한다.
*/
- (NSInteger) postrollCount;
```
## License

Gomcorp – (https://www.gomcorp.com/) – kuyoungchang@gomcorp.com

Copyright © 2017 Gomcorp.

[license-image]: https://img.shields.io/badge/License-MIT-blue.svg
[license-url]: LICENSE
