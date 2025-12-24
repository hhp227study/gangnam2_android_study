# Compose Navigation Integration Test Prompt

## 목표
Jetpack Compose 기반 앱에서 리스트 → 상세 화면 이동에 대한 통합 테스트 코드를 작성한다.

## 테스트 시나리오
1. 리스트 화면 표시
2. 특정 아이템 클릭
3. 상세 화면으로 네비게이션
4. 전달된 ID가 ViewModel에 정상 전달되었는지 검증
5. 뒤로 가기 시 리스트 상태 유지 여부 확인

## 제약 조건
- Navigation은 Compose 기반
- ViewModel은 Koin DI 사용
- Repository / UseCase는 Fake 구현 사용
- 실제 네트워크 호출 없음

## 검증 포인트
- UI 상 Navigation 발생 여부
- 전달된 recipeId 기반 상세 데이터 표시
- Back navigation 이후 상태 유지

## 기대 결과
- NavigationRoot 기준 통합 테스트 코드
- FakeRepository / FakeUseCase 활용
- Compose UI Test API 사용
