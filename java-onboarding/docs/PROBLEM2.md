## 🚀 기능 요구 사항

암호문을 좋아하는 괴짜 개발자 브라운이 이번에는 중복 문자를 이용한 새로운 암호를 만들었다. 예를 들어 "browoanoommnaon"이라는 암호문은 다음과 같은 순서로 해독할 수 있다.

1. "browoanoommnaon"
2. "browoannaon"
3. "browoaaon"
4. "browoon"
5. "brown"

임의의 문자열 cryptogram이 매개변수로 주어질 때, 연속하는 중복 문자들을 삭제한 결과를 return 하도록 solution 메서드를 완성하라.

### 제한사항

- cryptogram은 길이가 1 이상 1000 이하인 문자열이다.
- cryptogram은 알파벳 소문자로만 이루어져 있다.

### 실행 결과 예시

| cryptogram | result |
| --- | --- |
| "browoanoommnaon" | "brown" |
| "zyelleyz" | "" |

## 기능 목록 작성
- [x] 문자열을 리스트 형태로 변환해둔다.
- [x] 문자열을 돌면서 연속하며 중복된 곳이 있는지 검사한다.
- [x] 연속된 문자열을 공백 처리하여 없앤다.
- [x] 중복된 곳이 없다면 문자열을 반환한다.
- [x] 남은 숫자가 두 개이면서 두 값이 같다면 리스트를 비우고 종료한다.
