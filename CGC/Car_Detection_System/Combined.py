import cv2

cap = cv2.VideoCapture('video2.mp4')
car_cascade = cv2.CascadeClassifier('cars.xml')
fgbg = cv2.createBackgroundSubtractorMOG2()
counter = 0

while True:
    ret, frame = cap.read()
    if not ret:
        break
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    cars = car_cascade.detectMultiScale(gray, 1.5, 1)
    car_mask = gray.copy()
    motion_mask = gray.copy()
    for (x, y, w, h) in cars:
        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 0, 255), 2)
        cv2.rectangle(car_mask, (x, y), (x+w, y+h), 255, -1)
        counter += 1
    fgmask = fgbg.apply(gray)
    motion_mask[fgmask == 255] = 255
    combined_mask = cv2.bitwise_or(car_mask, motion_mask)
    result = cv2.bitwise_and(frame, frame, mask=combined_mask)
    cv2.putText(result, "Car count: {}".format(counter), (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2)
    cv2.imshow('Car Detection', result)
    if cv2.waitKey(1) == ord('q'):
        break
cap.release()
cv2.destroyAllWindows()
