# 게임 진행 흐름 🚇
- `초기 정보`를 설정합니다. 제공된 역, 노선, 구간을 저장해둡니다.
  - `역`은 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역을 저장해 두었습니다.
  - `노선` 정보는 2호선, 3호선, 신분당선을 저장해 두었습니다. 노선은 이름, 역 목록을 가집니다.
  - `구간` 정보는 2호선에서의 구간, 3호선에서의 구간, 신분당선에서의 구간을 저장해 두었습니다. (구간: 출발역, 도착역, 소요 시간, 거리)
- `첫 시작 명령어`를 입력받습니다. 제대로 입력 받지 못했을 시 반복하여 물어봅니다.
- `경로 기준 명령어`를 입력받습니다. `돌아가기`일 시 `첫 시작 명령어`부터 다시 물어봅니다.
  - `최단 거리`, `최소 시간`을 입력하였을 경우 `출발역`과 `도착역`을 입력받습니다. 이 과정에서 예외가 발생한다면 `경로 기준 명령어`부터 다시 입력받습니다.
  - 만약 다시 입력받은 경로 기준 명령어가 `돌아가기`였다면 빈 목록을 반환합니다. `첫 시작 명령어`와 맞추기 위함입니다.
  - `최단 거리`, `최소 시간`에 따라 그래프의 가중치를 설정해 둡니다.
- `PathManager`를 이용하여 `최종 방문 목록` 및 `소요 시간`, `거리`를 구한 뒤 출력합니다.
* * *
# 기능 명세서
## 초기 설정
- [x] 역 정보 설정
  - [x] 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역
- [x] 노선 정보 설정
  - [x] 2호선, 3호선, 신분당선
- [x] 구간 정보 설정
  - [x] 2호선 설정
  - [x] 3호선 설정
  - [x] 신분당선 설정
## 경로 조회
- [x] 출발역 입력
- [x] 도착역 입력
- [x] 총 거리 출력
- [x] 총 소요 시간 출력
### 기준 설정
- [x] 최단 거리
- [x] 최소 시간
### 예외
- [x] 등록되어 있지 않은 역 이름일 시 예외
- [x] 출발역과 도착역이 같을 경우 예외
- [x] 출발역과 도착역이 연결되어 있지 않을 경우 예외
- [x] 그 외 비정상 경우 예외
## 명령어 입력
### 첫 명령어
- [x] 1: 경로 조회
- [x] Q: 종료
- [x] 그 외 입력: 예외 처리
### 경로 기준 명령어
- [x] 1: 최단 거리
  - [x] 예외: `출발역`, `도착역` 조회 도중 예외 발생 시 `경로 기준 명령어`를 다시 조회하는가?
- [x] 2: 최소 시간
  - [x] 예외: `출발역`, `도착역` 조회 도중 예외 발생 시 `경로 기준 명령어`를 다시 조회하는가?
- [x] B: 돌아가기 - `첫 명령어`로 이동
- [x] 그 외 입력: 예외 처리
## 거리
- [x] 양의 정수 (기본 생성만 활용하므로 예외처리 X)
  - [x] 예외: 0일 시 예외가 발생하는가?
  - [x] 예외: 음수일 시 예외가 발생하는가?
- [x] 단위: km
## 소요 시간
- [x] 양의 정수 (기본 생성만 활용하므로 예외처리 X)
  - [x] 예외: 0일 시 예외가 발생하는가?
  - [x] 예외: 음수일 시 예외가 발생하는가?
- [x] 단위: 분
## 구간 관리
- [x] `출발역`, `도착역`, `소요 시간`, `소요 거리`를 관리하도록 설정
- [x] enum으로 상수화 (초기 정보 저장에 활용)
- [x] 예외: `출발역`, `도착역`으로 검색할 때 없을 시 예외가 발생하는가?
- [x] 예외: `출발역`과 `도척역`이 같을 시 예외가 발생하는가?
* * *
# 기타 정보
* 주어진 데이터 환경에서는 모든 역들이 다 이어져 있습니다. 만약 이어져 있지 않은 경우를 만들었다면, 다익스트라 알고리즘에서 `NullPointerException`이 발생함을 발견했습니다. 따라서 이 예외가 발생했을 시 `[ERROR] 출발역과 도착역이 연결되어 있지 않아 경로 탐색이 불가능합니다.`를 출력하도록 했고, 그 이외의 예외가 발생했다면 `[ERROR] 알 수 없는 오류가 발생했습니다.`를 출력하도록 했습니다. 또한 `그래프에 속하지 않았을 경우`에도 전자에 쓰인 예외 (경로 탐색 불가능)가 나오도록 했습니다.
* 경로 탐색 시 `노선`은 쓰이지 않습니다.
