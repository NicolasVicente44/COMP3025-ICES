using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CloudController : MonoBehaviour
{
    public float verticalSpeed;
    public float topVeticalSpeed;
    public float lowestVerticalSpeed;
    public float topBound;
    public float bottomBound;
    public float leftBound;
    public float rightBound;
    public float lowestDrift;
    public float highestDrift;
    public float driftAmount;

    // Start is called before the first frame update
    void Start()
    {
        ResetGameObject();
    }

    // Update is called once per frame
    void Update()
    {
        Move();
        CheckBounds();
    }

    void Move()
    {
        float moveX = driftAmount; // Horizontal movement
        float moveY = verticalSpeed; // Vertical movement

        // Update position
        transform.position -= new Vector3(moveX, moveY, 0);
    }


    void ResetGameObject()
    {
        float randomX = Random.Range(leftBound, rightBound);

        randomX = Mathf.Clamp(randomX, leftBound, rightBound);

        transform.position = new Vector3(randomX, topBound, 0);

        verticalSpeed = Random.Range(lowestVerticalSpeed, topVeticalSpeed);
        driftAmount = Random.Range(lowestDrift, highestDrift);
    }




    void CheckBounds()
    {
        if (transform.position.y <= bottomBound)
        {
            ResetGameObject();
        }
    }
}