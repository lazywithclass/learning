#!/usr/bin/env bash

code1=$(curl -s -o /dev/null -w "%{http_code}" localhost:8000)
exit_code1=$?
code2=$(curl -s -o /dev/null -w "%{http_code}" localhost:9000)
exit_code2=$?


if [[ $exit_code1 -eq 0 && $code1 -eq 200 && $exit_code2 -eq 0 && $code2 -eq 200 ]]; then
  echo "All good: Both localhost:8000 and localhost:9000 returned 200."
  exit 0
else
  if [[ $exit_code1 -ne 0 ]]; then
    echo "Test failed: Could not connect to localhost:8000 (Curl Exit Code: $exit_code1)"
  elif [[ $code1 -ne 200 ]]; then
    echo "Test failed: localhost:8000 did not return 200 (Got: $code1)"
  fi

  if [[ $exit_code2 -ne 0 ]]; then
    echo "Test failed: Could not connect to localhost:9000 (Curl Exit Code: $exit_code2)"
  elif [[ $code2 -ne 200 ]]; then
    echo "Test failed: localhost:9000 did not return 200 (Got: $code2)"
  fi
  
  exit 1
fi
