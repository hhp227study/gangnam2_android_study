# SearchRoot 통합 테스트 코드 작성을 위한 프롬프트

## 역할
너는 Android Jetpack Compose 기반 앱의 통합 테스트를 작성하는 시니어 안드로이드 엔지니어다.

## 목표
SearchRoot 컴포저블에 대해 다음 시나리오를 검증하는 **통합 테스트 코드**를 작성한다.

## 시나리오
1. 사용자가 검색창에 키워드를 입력한다
2. debounce 시간이 지난 후
3. Fake Repository에서 검색 결과를 받아
4. 결과가 LazyGrid에 표시된다

## 제약 조건
- 실제 서버 호출 ❌
- Fake Repository 사용 ⭕
- ViewModel은 실제 객체 사용
- UI Test는 createAndroidComposeRule 사용
- debounce 처리를 위해 TestCoroutineScheduler 활용

## 검증 포인트
- 검색어 입력 시 ViewModel 상태 변경
- debounce 이후 UseCase 호출
- Fake Repository → 결과 반환
- UI에 검색 결과 렌더링
- 결과 개수 텍스트 표시 여부

## 출력물
- Fake Repository
- Fake UseCase
- SearchRoot 통합 테스트 코드
- 각 테스트 단계에 대한 주석 설명

## 기술 스택
- Jetpack Compose
- StateFlow
- ViewModel
- Coroutine Test
- Compose UI Test