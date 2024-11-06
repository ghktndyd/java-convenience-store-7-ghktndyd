# java-convenience-store-precourse

## 구현할 기능 목록

---

- [ ] **현재 보유하고 있는 상품 재고를 등록하기**
    - [ ] 상품 재고를 등록한다.
        - [ ] `products.md` 파일을 읽어온다.
            - [ ] 파일이 빈 파일이 아님을 검증한다.
            - [ ] 파일의 헤더가 정의된 형태인지를 검증한다. (name, price, quantity, promotion)
            - [ ] `price`와 `quantity` 컬럼이 유효한 숫자 값인지 검증한다.
            - [ ] 프로모션의 내용이 `null` 혹은 `promotions.md` 파일에 존재하는 프로모션인지를 검증한다.

- [x] **현재 진행 중인 프로모션 등록하기**
    - [x] 프로모션 정보를 등록한다.
        - [x] `promotions.md` 파일을 읽어온다.
            - [x] 파일이 빈 파일이 아님을 검증한다.
            - [x] 파일의 헤더가 정의된 형태인지를 검증한다. (name, buy, get, start_date, end_date)
            - [x] `buy`와 `get` 컬럼이 유효한 숫자 값인지 검증한다.
            - [x] `start_date`와 `end_date`가 유효한 날짜 형식인지 검증한다. (YYYY-MM-DD)

- [ ] **보유하고 있는 재고를 출력하기**
    - [ ] 프로그램 실행시 환영 문구를 출력한다.
    - [ ] 재고 보유 현황을 출력 형태에 맞게 출력한다.
        - [ ] 상품명, 가격, 수량, 프로모션 정보를 포함한다.
        - [ ] 재고가 0이면 "재고 없음"으로 표현한다.

- [ ] **구매할 상품명과 수량을 입력받기**
    - [ ] 사용자가 구매할 상품명과 수량의 입력을 요청하는 문구를 출력한다.
    - [ ] 상품명과 수량을 입력받는다.
        - [ ] `[상품명-수량], [상품명-수량]...` 형태인지 검증한다.
        - [ ] 각 상품명이 실제로 존재하는 상품인지를 검증한다.
        - [ ] 각 수량이 숫자 형태인지를 검증한다.
        - [ ] 각 구매 수량이 재고 수량을 초과하지 않는지를 검증한다.

- [ ] **프로모션이 적용 관련 사항 안내 및 적용 여부를 입력받기**
    - [ ] 프로모션 적용이 가능한 상태라면 상품 추가 여부 입력 요청 및 입력받는다.
        - [ ] 입력 값이 `Y` 또는 `N`임을 검증한다.
    - [ ] 프로모션이지만 재고 부족시 일반 가격 구매 여부 입력 요청 및 입력받는다.
        - [ ] 입력 값이 `Y` 또는 `N`임을 검증한다.

- [ ] **멤버십 할인 여부 입력받기**
    - [ ] 입력 값이 `Y` 또는 `N`임을 검증한다.

- [ ] **결제 내역 영수증 출력하기**
    - [ ] 결제 금액을 계산한다.
        - [ ] 총 구매 금액을 계산한다.
        - [ ] 프로모션 적용 할인 금액을 계산한다.
        - [ ] 멤버십 할인 금액을 계산한다. (최대 8,000원)
        - [ ] 최종 결제 금액을 계산한다.
    - [ ] 영수증을 생성한다.
        - [ ] 구매 상품 내역을 포함한다.
        - [ ] 증점 상품 내역을 포함한다.
        - [ ] 결제 금액 내역을 포함한다.